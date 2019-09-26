package com.admxj.real;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @author jin.xiang
 * @version Id: MyServerInitializer, v 0.1 2019-09-26 15:55 jin.xiang Exp $
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        /* 处理http服务的关键handler */
        ChannelPipeline ph = ch.pipeline();
//        ph.addLast("idlestatus", getIdleStateHandler());
        ph.addLast("encoder", new HttpResponseEncoder());
        ph.addLast("decoder", new HttpRequestDecoder());
        ph.addLast("aggregator", new HttpObjectAggregator(10485760));
        // 服务端业务逻辑
        ph.addLast("handler", new MyServerHandler());
    }
}
