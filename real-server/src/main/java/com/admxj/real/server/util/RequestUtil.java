package com.admxj.real.server.util;

import io.netty.handler.codec.http.HttpRequest;

import static com.admxj.real.core.constant.Symbol.QUESTION_MARK;

/**
 * @author admxj
 * @version Id: RequestUtil, v 0.1 2019-09-28 15:23 admxj Exp $
 */
public class RequestUtil {

    /**
     * 从uri中提取最末端
     *
     * @param request 请求
     * @return string
     */
    public static String getUriName(HttpRequest request) {
        /* 获取路径 */
        String uri = request.uri();
        if (uri.indexOf(QUESTION_MARK) > -1) {
            uri = uri.substring(0, uri.indexOf("?"));
        }
        return uri;
    }

    /**
     * 从uri中提取 请求连接的最末端，用来匹配控制层映射
     *
     * @param uri
     * @return
     */
    public static String getRequestPath(String uri) {
        /* 获取路径 */
        if (uri.startsWith("/")) {
            uri = uri.substring(1);
        }
        return uri;
    }
}
