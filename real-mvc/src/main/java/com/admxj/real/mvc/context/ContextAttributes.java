package com.admxj.real.mvc.context;

import java.util.Collections;
import java.util.Map;

/**
 * @author jin.xiang
 * @version Id: ContextAttributes, v 0.1 2019-10-03 01:29 jin.xiang Exp $
 */
public class ContextAttributes {

    private Map<String, Object> attributeMap;

    // public 方法 ------------------------------------------------------------------------

    /**
     * 获取所有属性
     */
    public Map<String, Object> getAttributeMap() {
        return Collections.unmodifiableMap(this.attributeMap);
    }

    /**
     * 获取属性
     * @param key
     * @return
     */
    public Object getAttribute(String key) {
        return attributeMap.get(key);
    }

    /**
     * 设置属性
     * @param key
     * @param value
     */
    public void setAttribute(String key, Object value) {
        this.attributeMap.put(key, value);
    }

}
