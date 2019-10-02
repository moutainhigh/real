package com.admxj.real.mvc.resolve.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

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

/**
 * @author jin.xiang
 * @version Id: ResourceProcess, v 0.1 2019-10-03 01:12 jin.xiang Exp $
 */
public class ResourceProcess {

    private static ResourceProcess resourceProcess;

    private ResourceProcess() {
    }

    public static ResourceProcess getResourceProcess() {
        if (null == resourceProcess) {
            resourceProcess = new ResourceProcess();
        }
        return resourceProcess;
    }

    // 资源所在路径
    private static final String location;

    // 404文件页面地址
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

        try (RandomAccessFile file = new RandomAccessFile(html, "r")) {
            ChannelHandlerContext ctx = response.getCtx();

            // 文件没有发现设置状态为404
            if (html == NOT_FOUND) {
                response.setStatus(HttpResponseStatus.NOT_FOUND.code());
            }

            // 设置文件格式内容
            if (path.endsWith(".html")) {
                response.addHeader(HttpHeaderNames.CONTENT_TYPE.toString(), "text/html; charset=UTF-8");
            } else if (path.endsWith(".js")) {
                response.addHeader(HttpHeaderNames.CONTENT_TYPE.toString(), "application/x-javascript");
            } else if (path.endsWith(".css")) {
                response.addHeader(HttpHeaderNames.CONTENT_TYPE.toString(), "text/css; charset=UTF-8");
            }

            // TODO: 2019-10-03 jin.xiang 默认支持 keepAlive
            boolean keepAlive = true;
            //            keepAlive = HttpHeaders.isKeepAlive(request);

            if (keepAlive) {
                response.addHeader(HttpHeaderNames.CONTENT_LENGTH.toString(), String.valueOf(file.length()));
                response.addHeader(HttpHeaderNames.CONNECTION.toString(), HttpHeaderValues.KEEP_ALIVE.toString());
            }

            ctx.write(response);

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

}
