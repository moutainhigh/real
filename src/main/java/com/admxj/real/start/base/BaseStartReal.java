package com.admxj.real.start.base;

import com.admxj.real.base.StartMap;
import com.admxj.real.netty.RealServer;

import java.util.Map;

/**
 * @author admxj
 * @version Id: BaseStartReal, v 0.1 2019-09-27 02:30 admxj Exp $
 */
public class BaseStartReal {

    private static Map<Integer, StartMap> startList;

    public static void setStartList(Map<Integer, StartMap> startList) {
        BaseStartReal.startList = startList;
    }

    public static void start(Class<?> clazz) {

        try {
            StartLoad.load(clazz, startList);

            RealServer.start(8088);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
