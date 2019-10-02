package com.admxj.real.mvc.model;

import com.admxj.real.mvc.model.enums.RequestMethod;
import lombok.Data;

import java.lang.annotation.Annotation;

/**
 * @author jin.xiang
 * @version Id: RealMappingModel, v 0.1 2019-09-27 17:30 jin.xiang Exp $
 */
@Data
public class RealMappingModel {

    /**
     * 对象
     */
    private Object        object;

    /**
     * 请求方式
     */
    private RequestMethod requestMethod;

    /**
     * 映射的方法
     */
    private String        method;

    /**
     * 控制层class对象
     */
    private Class<?>      cls;

    /**
     * @see com.admxj.real.mvc.annotation.RestController
     * @see com.admxj.real.mvc.annotation.Controller
     * 类上注解,支持RestController, Controller
     */
    private Annotation    annotation;
}
