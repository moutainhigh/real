package com.admxj.real.server.handler;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * @author jin.xiang
 * @version Id: HttpServerHandleAdapter, v 0.1 2019-10-02 05:17 jin.xiang Exp $
 */
public class HttpServerHandleAdapter extends SimpleChannelInboundHandler<FullHttpRequest> {

    public static final String  CONTENT_TYPE   = "Content-Type";

    public static final String  CONTENT_LENGTH = "Content-Length";

    public static final String  CONNECTION     = "Connection";

    public static final String  KEEP_ALIVE     = "keep-alive";

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

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        // 获取URI
        String uri = request.getUri();
        // 设置不支持favicon.ico文件
        if ("favicon.ico".equals(uri)) {
            return;
        }
        // 根据路径地址构建文件
        String path = location + uri;
        File html = new File(path);

        // 状态为1xx的话，继续请求
        if (HttpHeaders.is100ContinueExpected(request)) {
            send100Continue(ctx);
        }

        // 当文件不存在的时候，将资源指向NOT_FOUND
        if (!html.exists()) {
            html = NOT_FOUND;
        }

        RandomAccessFile file = new RandomAccessFile(html, "r");
        HttpResponse response = new DefaultHttpResponse(request.getProtocolVersion(), HttpResponseStatus.OK);

        // 文件没有发现设置状态为404
        if (html == NOT_FOUND) {
            response.setStatus(HttpResponseStatus.NOT_FOUND);
        }

        // 设置文件格式内容
        if (path.endsWith(".html")) {
            response.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");
        } else if (path.endsWith(".js")) {
            response.headers().set(CONTENT_TYPE, "application/x-javascript");
        } else if (path.endsWith(".css")) {
            response.headers().set(CONTENT_TYPE, "text/css; charset=UTF-8");
        }

        boolean keepAlive = HttpHeaders.isKeepAlive(request);

        if (keepAlive) {
            response.headers().set(CONTENT_LENGTH, file.length());
            response.headers().set(CONNECTION, KEEP_ALIVE);
        }
        ctx.write(response);

        if (ctx.pipeline().get(SslHandler.class) == null) {
            ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));
        } else {
            ctx.write(new ChunkedNioFile(file.getChannel()));
        }
        // 写入文件尾部
        ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
        if (!keepAlive) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
        file.close();
    }

    private static void send100Continue(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
        ctx.writeAndFlush(response);
    }
}
