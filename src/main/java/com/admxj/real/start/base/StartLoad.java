package com.admxj.real.start.base;

import com.admxj.real.base.StartMap;
import com.admxj.real.base.StartParam;

import java.util.Map;

/**
 * @author admxj
 * @version Id: StartLoad, v 0.1 2019-09-27 02:32 admxj Exp $
 */
public class StartLoad {

    public static void load(Map<Integer, StartMap> startList) throws Exception {

        StartParam startParam = new StartParam();
        for (int i =0; i < startList.size(); i++){
            startList.get(i).load(startParam);
        }
    }
}
