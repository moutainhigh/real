package com.admxj.real.server.model.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author jin.xiang
 * @version Id: HttpResponse, v 0.1 2019-09-27 09:41 jin.xiang Exp $
 */
@Data
public class HttpResponse {

    /**
     * 响应体
     */
    private String                body;

    /**
     * 响应头
     */
    private Map<String, String>   headers;

    /**
     * netty原生通道
     */
    private ChannelHandlerContext ctx;

    /**
     * 响应头
     */
    private Map<String, String>   header;

    /**
     * 响应状态码
     */
    private Integer               status;

    public HttpResponse(ChannelHandlerContext ctx) {
        this.ctx = ctx;
        header = new HashMap<>();
    }

    public void send() {

        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.copiedBuffer(body.toString(), CharsetUtil.UTF_8));

        crossDomain(response);
        loadHeader(response);

        HttpHeaders nettyHeaders = response.headers();
        header.forEach((key, value) -> nettyHeaders.add(key, value));

        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

    }

    /**
     * 新增header
     *
     * @param name
     * @param value
     */
    public void addHeader(String name, String value) {
        if (null == header) {
            header = new LinkedHashMap<>();
        }
        header.put(name, value);
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
