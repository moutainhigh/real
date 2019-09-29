package com.admxj.real.base;

import com.admxj.real.base.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jin.xiang
 * @version Id: StartLoadList, v 0.1 2019-09-26 18:28 jin.xiang Exp $
 */
public class StartLoadList {

    public static Map<Integer, StartMap> initStartList(){

        Map<Integer, StartMap> startList = new HashMap<>(100);

        startList.put(0, new StartCoreServlet());
        startList.put(1, new StartConfig());
        startList.put(2, new StartBeans());
        startList.put(3, new StartJdbc());
        startList.put(4, new StartBeanObject());
        startList.put(5, new StartController());
//        startList.put(6, new StartInter());
//        startList.put(7, new HasStart());
//        startList.put(8, new StartMarsTimer());
//        startList.put(9, new StartLoadAfter());
//        startList.put(10, new StartExecuteTimer());
        return startList;
    }

}
