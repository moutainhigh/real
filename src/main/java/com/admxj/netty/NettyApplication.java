package com.admxj.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author jin.xiang
 * @version Id: NettyApplication, v 0.1 2019-09-26 15:15 jin.xiang Exp $
 */
public class NettyApplication {

    static int portNumber = 8088;

    public static void main(String[] args) {

        try {
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup workerGroup = new NioEventLoopGroup();

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup);
            bootstrap.channel(NioServerSocketChannel.class);

            bootstrap.childHandler(new MyServerInitializer());

            /* 服务器绑定端口监听 */
            ChannelFuture future = bootstrap.bind(portNumber).sync();

            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
