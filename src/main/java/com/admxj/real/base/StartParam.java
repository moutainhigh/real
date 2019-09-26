package com.admxj.real.base;

import lombok.Data;

/**
 * @author admxj
 * @version Id: StartParam, v 0.1 2019-09-27 02:05 admxj Exp $
 */
@Data
public class StartParam {

    /**
     * 启动类
     */
    private Class<?> clazz;

    /**
     * 启动类所在的包
     */
    private String startClassName;

}
