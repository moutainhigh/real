package com.admxj.real.server;

import com.admxj.real.server.handler.MyServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author admxj
 * @version Id: RealServer, v 0.1 2019-09-27 02:36 admxj Exp $
 */
public class RealServer {
    
    
    public static void start(int portNumber) {

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
