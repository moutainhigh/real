package com.admxj.real.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jin.xiang
 * @version Id: HttpResponse, v 0.1 2019-09-27 09:41 jin.xiang Exp $
 */
public class HttpResponse {

    /**
     * netty原生通道
     */
    private ChannelHandlerContext ctx;

    /**
     * 响应头
     */
    private Map<String, String> header;

    public HttpResponse(ChannelHandlerContext ctx) {
        this.ctx = ctx;
        header = new HashMap<>();
    }

    public void send(String content) {

        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                Unpooled.copiedBuffer("hello", CharsetUtil.UTF_8));

        crossDomain(response);
        loadHeader(response);

        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/json; charset=UTF-8");

        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

    }

    /**
     * 设置跨域
     *
     * @param response
     */
    private void crossDomain(FullHttpResponse response) {
    }


    /**
     * 加载设置的header
     *
     * @param response
     */
    private void loadHeader(FullHttpResponse response) {

    }
}
