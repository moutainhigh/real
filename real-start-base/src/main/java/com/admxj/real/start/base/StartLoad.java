package com.admxj.real.start.base;

import com.admxj.real.core.StartMap;
import com.admxj.real.core.StartParam;

import java.util.Map;

/**
 * @author admxj
 * @version Id: StartLoad, v 0.1 2019-09-27 02:32 admxj Exp $
 */
public class StartLoad {

    public static void load(Class<?> clazz, Map<Integer, StartMap> startList) throws Exception {

        StartParam startParam = new StartParam();
        startParam.setClazz(clazz);
        for (int i = 0, len = startList.size(); i < len; i++) {
            startList.get(i).load(startParam);
        }
    }
}
