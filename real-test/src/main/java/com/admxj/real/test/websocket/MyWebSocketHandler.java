//package com.admxj.real;
//
//import io.real.buffer.ByteBuf;
//import io.real.buffer.Unpooled;
//import io.real.channel.ChannelFuture;
//import io.real.channel.ChannelFutureListener;
//import io.real.channel.ChannelHandlerContext;
//import io.real.channel.SimpleChannelInboundHandler;
//import io.real.handler.codec.http.*;
//import io.real.handler.codec.http.websocketx.*;
//import io.real.util.CharsetUtil;
//
//import java.util.Date;
//
///**
// * @author admxj
// * @version Id: MyWebSocketHandler, v 0.1 2019-09-26 08:21 admxj Exp $
// */
//public class MyWebSocketHandler extends SimpleChannelInboundHandler<Object> {
//
//    private WebSocketServerHandshaker handshaker;
//
//    private static final String WEB_SOCKET_URL = "ws://localhost:8888/websocket";
//
//    @Override
//    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
//        // 握手请求
//        if (msg instanceof FullHttpMessage) {
//            handHttpRequest(ctx, (FullHttpRequest) msg);
//        } else if (msg instanceof WebSocketFrame) {
//            handWebsocketFrame(ctx, (WebSocketFrame) msg);
//        }
//    }
//
//    private void handWebsocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
//        if (frame instanceof CloseWebSocketFrame) {
//            handshaker.close(ctx.channel(), ((CloseWebSocketFrame) frame).retain());
//        }
//        if (frame instanceof PingWebSocketFrame) {
//            ctx.channel().write(new PongWebSocketFrame(frame.body().retain()));
//            return;
//        }
//        if (!(frame instanceof TextWebSocketFrame)) {
//            System.out.println("UnSupport Binary Message");
//            throw new RuntimeException("[" + this.getClass().getName() + "]");
//        }
//        String text = ((TextWebSocketFrame) frame).text();
//        System.out.println("收到消息：" + text);
//        TextWebSocketFrame twsf = new TextWebSocketFrame(new Date().toString() + ctx.channel().id() + "====>" + text);
//        twsf.retain();
//
//        NettyConfig.group.writeAndFlush(twsf);
//    }
//
//    /**
//     * @param ctx
//     * @param req
//     */
//    private void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
//        if (!req.getDecoderResult().isSuccess() || !("websocket".equals(req.headers().get("Upgrade")))) {
//            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
//        }
//        WebSocketServerHandshakerFactory handshakerFactory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL, null, false);
//        handshaker = handshakerFactory.newHandshaker(req);
//        if (null == handshaker) {
//            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
//        } else {
//            handshaker.handshake(ctx.channel(), req);
//        }
//
//
//    }
//
//    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
//        if (res.getStatus().code() == 200) {
//            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
//            res.body().writeBytes(buf);
//            buf.release();
//        }
//        ChannelFuture f = ctx.channel().read().write(res);
//        if (res.getStatus().code() != 200) {
//            f.addListener(ChannelFutureListener.CLOSE);
//        }
//    }
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        NettyConfig.group.add(ctx.channel());
//        System.out.println("连接开启");
//    }
//
//    @Override
//    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        NettyConfig.group.remove(ctx.channel());
//        System.out.println("连接关闭");
//    }
//
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ctx.flush();
//        System.out.println("channelReadComplete");
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
//        ctx.close();
//    }
//}
