//package com.admxj.real;
//
//import io.real.bootstrap.ServerBootstrap;
//import io.real.channel.Channel;
//import io.real.channel.EventLoopGroup;
//import io.real.channel.nio.NioEventLoopGroup;
//import io.real.channel.socket.nio.NioServerSocketChannel;
//
///**
// * @author admxj
// * @version Id: StartNetty, v 0.1 2019-09-26 08:56 admxj Exp $
// */
//public class StartNetty {
//
//
//    public static void main(String[] args) {
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        EventLoopGroup workGroup = new NioEventLoopGroup();
//
//        try {
//            ServerBootstrap b = new ServerBootstrap();
//            b.group(bossGroup, workGroup);
//            b.channel(NioServerSocketChannel.class);
//            b.childHandler(new MyWebSocketChannelHandler());
//            System.out.println("服务启动完成");
//            Channel channel = b.bind(8888).sync().channel();
//            channel.close().sync();
//
//        }catch (Exception e){
//
//        }finally {
//            bossGroup.shutdownGracefully();
//            workGroup.shutdownGracefully();
//        }
//    }
//
//}
