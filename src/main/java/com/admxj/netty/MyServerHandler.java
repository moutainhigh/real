package com.admxj.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.InetAddress;

/**
 * @author jin.xiang
 * @version Id: MyServerHandler, v 0.1 2019-09-26 16:13 jin.xiang Exp $
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest httpRequest = null;
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                Unpooled.copiedBuffer("hello", CharsetUtil.UTF_8));

        try {
            if (msg instanceof FullHttpRequest) {
                httpRequest = (FullHttpRequest) msg;
                ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
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

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("客户端" + InetAddress.getLocalHost().getHostName() + "成功与服务端建立连接！ ");
        super.channelActive(ctx);
    }
}
