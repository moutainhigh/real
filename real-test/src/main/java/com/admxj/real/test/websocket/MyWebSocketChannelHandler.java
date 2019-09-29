//package com.admxj.real;
//
//import io.real.channel.ChannelInitializer;
//import io.real.channel.socket.SocketChannel;
//import io.real.handler.codec.http.HttpObjectAggregator;
//import io.real.handler.codec.http.HttpServerCodec;
//import io.real.handler.stream.ChunkedWriteHandler;
//
///**
// * @author admxj
// * @version Id: MyWebSocketChannelHandler, v 0.1 2019-09-26 08:53 admxj Exp $
// */
//public class MyWebSocketChannelHandler extends ChannelInitializer<SocketChannel> {
//
//    @Override
//    protected void initChannel(SocketChannel channel) throws Exception {
//        channel.pipeline().addLast("http-codex", new HttpServerCodec());
//        channel.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
//        channel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
//        channel.pipeline().addLast("handler", new MyWebSocketHandler());
//    }
//}
