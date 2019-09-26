package com.admxj.real.base;

/**
 * @author jin.xiang
 * @version Id: StartMap, v 0.1 2019-09-26 18:30 jin.xiang Exp $
 */
public interface StartMap {

    /**
     * 要加载的东西
     * @param startParam 参数
     */
    void load(StartParam startParam) throws Exception ;
}
