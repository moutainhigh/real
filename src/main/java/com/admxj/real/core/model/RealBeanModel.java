package com.admxj.real.core.model;

import lombok.Data;

/**
 * @author jin.xiang
 * @version Id: RealBeanModel, v 0.1 2019-09-27 15:29 jin.xiang Exp $
 */
@Data
public class RealBeanModel {

    /**
     * bean名称
     */
    private String name;

    /**
     * bean对象
     */
    private Object obj;

    /**
     * class对象
     */
    private Class<?> cls;

}
