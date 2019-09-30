package com.admxj.real.test;

import com.admxj.real.core.annotation.BeanScan;
import com.admxj.real.start.RearStart;

/**
 * @author jin.xiang
 * @version Id: NettyApplication, v 0.1 2019-09-26 15:15 jin.xiang Exp $
 */
@BeanScan()
public class NettyApplication {

    public static void main(String[] args) {
        RearStart.start(NettyApplication.class, args);
    }
}
