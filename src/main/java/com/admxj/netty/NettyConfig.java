package com.admxj.netty;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author admxj
 * @version Id: NettyConfig, v 0.1 2019-09-26 08:15 admxj Exp $
 */
public class NettyConfig {

    /**
     * 存储每一个客户端的channel
     */
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


}
