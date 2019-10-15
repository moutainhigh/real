package com.admxj.real.mvc.resolve.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.*;

import com.admxj.real.mvc.context.ProcessContext;
import com.admxj.real.mvc.model.RealMappingModel;
import com.admxj.real.server.handler.HttpServerHandleAdapter;
import com.admxj.real.server.model.http.HttpRequest;
import com.admxj.real.server.model.http.HttpResponse;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import javax.activation.MimetypesFileTypeMap;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author jin.xiang
 * @version Id: ResourceProcess, v 0.1 2019-10-03 01:12 jin.xiang Exp $
 */
public class ResourceProcess {

    public static final String     HTTP_DATE_FORMAT       = "EEE, dd MMM yyyy HH:mm:ss zzz";
    public static final String     HTTP_DATE_GMT_TIMEZONE = "GMT";
    public static final int        HTTP_CACHE_SECONDS     = 60;

    private static ResourceProcess resourceProcess;

    private ResourceProcess() {
    }

    public static ResourceProcess getResourceProcess() {
        if (null == resourceProcess) {
            resourceProcess = new ResourceProcess();
        }
        return resourceProcess;
    }

    /** 资源所在路径 */
    private static final String location;

    /** 404文件页面地址 */
    private static final File   NOT_FOUND;

    static {
        // 构建资源所在路径，此处参数可优化为使用配置文件传入
        location = HttpServerHandleAdapter.class.getResource("/static").getFile();
        // 构建404页面
        String path = location + "/404.html";
        NOT_FOUND = new File(path);
    }

    public void process(ProcessContext<RealMappingModel> context) {

        HttpRequest request = context.getRequest();
        HttpResponse response = context.getResponse();

        // 获取URI
        String uri = request.getHttpRequest().uri();
        // 设置不支持favicon.ico文件
        if ("favicon.ico".equals(uri)) {
            return;
        }
        // 根据路径地址构建文件
        String path = location + uri;
        File html = new File(path);

        // 当文件不存在的时候，将资源指向NOT_FOUND
        if (!html.exists()) {
            html = NOT_FOUND;
        }

        DefaultHttpResponse httpResponse = new DefaultHttpResponse(HTTP_1_1, HttpResponseStatus.valueOf(response.getStatus() != null ? response.getStatus() : 200));

        try (RandomAccessFile file = new RandomAccessFile(html, "r")) {
            ChannelHandlerContext ctx = response.getCtx();

            // 文件没有发现设置状态为404
            if (!html.exists()) {
                response.setStatus(HttpResponseStatus.NOT_FOUND.code());
            }

            boolean keepAlive = true;

            if (keepAlive) {
                response.addHeader(HttpHeaderNames.CONTENT_LENGTH.toString(), String.valueOf(file.length()));
                response.addHeader(HttpHeaderNames.CONNECTION.toString(), HttpHeaderValues.KEEP_ALIVE.toString());
            }

            setContentLength(httpResponse, html);
            setContentTypeHeader(httpResponse, html);
            setDateAndCacheHeaders(httpResponse, html);

            if (HttpUtil.isKeepAlive(httpResponse)) {
                httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            }
            ctx.write(httpResponse);

            if (ctx.pipeline().get(SslHandler.class) == null) {
                ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));
            } else {
                ctx.write(new ChunkedNioFile(file.getChannel()));
            }
            ctx.flush();
            // 写入文件尾部
            ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            if (!keepAlive) {
                future.addListener(ChannelFutureListener.CLOSE);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setContentLength(DefaultHttpResponse httpResponse, File file) {
        httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, file.length());
    }

    private static void setDateAndCacheHeaders(io.netty.handler.codec.http.HttpResponse response, File fileToCache) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(HTTP_DATE_FORMAT, Locale.US);
        dateFormatter.setTimeZone(TimeZone.getTimeZone(HTTP_DATE_GMT_TIMEZONE));

        // Date header
        Calendar time = new GregorianCalendar();
        response.headers().set(HttpHeaderNames.DATE, dateFormatter.format(time.getTime()));

        // Add cache headers
        time.add(Calendar.SECOND, HTTP_CACHE_SECONDS);
        response.headers().set(HttpHeaderNames.EXPIRES, dateFormatter.format(time.getTime()));
        response.headers().set(HttpHeaderNames.CACHE_CONTROL, "private, max-age=" + HTTP_CACHE_SECONDS);
        response.headers().set(HttpHeaderNames.LAST_MODIFIED, dateFormatter.format(new Date(fileToCache.lastModified())));
    }

    /**
     * 设置文件格式内容
     * @param response
     * @param file
     */
    private static void setContentTypeHeader(io.netty.handler.codec.http.HttpResponse response, File file) {
        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, mimeTypesMap.getContentType(file.getPath()));
        String path = file.getPath();
        if (path.endsWith(".html")) {
            response.headers().set(HttpHeaderNames.CONTENT_TYPE.toString(), "text/html; charset=UTF-8");
        } else if (path.endsWith(".js")) {
            response.headers().set(HttpHeaderNames.CONTENT_TYPE.toString(), "application/x-javascript");
        } else if (path.endsWith(".css")) {
            response.headers().set(HttpHeaderNames.CONTENT_TYPE.toString(), "text/css; charset=UTF-8");
        }
    }

}
