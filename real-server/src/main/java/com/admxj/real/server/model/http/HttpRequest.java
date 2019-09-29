package com.admxj.real.server.model.http;


import com.admxj.real.server.model.RealFileUpload;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author jin.xiang
 * @version Id: HttpRequest, v 0.1 2019-09-27 09:41 jin.xiang Exp $
 */
@Data
public class HttpRequest {


    private Logger logger = LoggerFactory.getLogger(HttpRequest.class);

    /**
     * netty原生request
     */
    private FullHttpRequest httpRequest;

    /**
     * netty原生通道
     */
    private ChannelHandlerContext ctx;

    /**
     * 请求体
     */
    private String body;

    /**
     * 参数
     */
    private Map<String, Object> parameters;

    /**
     * 请求的文件
     */
    private Map<String, RealFileUpload> files;

    /**
     * 获取请求方法
     *
     * @return
     */
    public HttpMethod getMethod() {
        return httpRequest.method();
    }

    public HttpRequest(FullHttpRequest httpRequest, ChannelHandlerContext ctx) {
        this.httpRequest = httpRequest;
        this.ctx = ctx;
    }

    /**
     * 从uri中提取最末端
     *
     * @return string
     */
    public String getUriName() {
        /* 获取路径 */
        String uri = httpRequest.uri();
        if (uri.indexOf("?") > -1) {
            uri = uri.substring(0, uri.indexOf("?"));
        }
        return uri;
    }
}
