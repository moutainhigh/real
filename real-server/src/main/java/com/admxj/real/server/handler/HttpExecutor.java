package com.admxj.real.server.handler;

import com.admxj.real.core.load.RealSpace;
import com.admxj.real.server.model.http.HttpRequest;
import com.admxj.real.server.model.http.HttpResponse;
import com.admxj.real.server.servlet.RealServlet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jin.xiang
 * @version Id: HttpExecutor, v 0.1 2019-10-02 16:34 jin.xiang Exp $
 */
public class HttpExecutor {

    private final static Logger logger = LoggerFactory.getLogger(HttpExecutor.class);

    /**
     *
     * 处理逻辑请求, 调用coreServlet
     * @param httpRequest nettyRequest
     * @param ctx 通道处理器上下文
     */
    public static void execute(FullHttpRequest httpRequest, ChannelHandlerContext ctx) {
        /* 组装httpRequest对象 */
        HttpRequest request = new HttpRequest(httpRequest, ctx);

        /* 组装httpResponse对象 */
        HttpResponse response = new HttpResponse(ctx);

        try {
            RealSpace constants = RealSpace.getEasySpace();
            String className = constants.getAttr("core").toString();
            Class<?> cls = Class.forName(className);
            RealServlet object = (RealServlet) cls.getDeclaredConstructor().newInstance();
            Method doRequest = cls.getDeclaredMethod("doRequest", HttpRequest.class, HttpResponse.class);

            doRequest.invoke(object, request, response);

            response.send();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放请求
                ctx.close();
                httpRequest.release();
            } catch (Exception e) {
                logger.error("释放请求出错", e);
            }
        }

    }
}
