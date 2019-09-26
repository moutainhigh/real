package com.admxj.real.constant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author admxj
 * @version Id: RealSpace, v 0.1 2019-09-27 03:01 admxj Exp $
 */
public class RealSpace {

    private static RealSpace constants;

    private Map<String, Object> map = new ConcurrentHashMap<>();

    public static RealSpace getEasySpace() {
        if (constants == null) {
            constants = new RealSpace();
        }
        return constants;
    }

    public void setAttr(String key,Object value) {
        map.put(key, value);
    }

}
