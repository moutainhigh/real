package com.admxj.real.core.load;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jin.xiang
 * @version Id: RealSpace, v 0.1 2019-09-29 16:16 jin.xiang Exp $
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

    public void setAttr(String key, Object value) {
        map.put(key, value);
    }

    public Object getAttr(String key) {
        return map.get(key);
    }

}
