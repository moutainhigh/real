package com.admxj.real.server.handler;

import com.admxj.real.core.load.RealSpace;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;

/**
 * @author jin.xiang
 * @version Id: MyServerHandler, v 0.1 2019-09-26 16:13 jin.xiang Exp $
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest httpRequest = null;


        try {
            if (msg instanceof FullHttpRequest) {
                httpRequest = (FullHttpRequest) msg;

                // TODO: 2019-09-28 admxj 待改造线程池
                execute(httpRequest, ctx);


            } else {
                System.out.println("UnSupport " + msg.toString());
            }

        } catch (Exception e) {
            System.out.println("Error :");
            e.printStackTrace();

            /* 已经通过RequestExecute中的finally 释放请求了，所以这里，在出异常的时候，才释放 */
            try {
                ctx.close();
                if (httpRequest != null) {
                    httpRequest.release();
                }
            } catch (Exception e2) {
            }
        }

    }

    public void execute(FullHttpRequest httpRequest, ChannelHandlerContext ctx) {

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
            Object result = doRequest.invoke(object, request, response);

            response.send(String.valueOf(result));
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

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("客户端" + InetAddress.getLocalHost().getHostName() + "成功与服务端建立连接！ ");
        super.channelActive(ctx);
    }
}
