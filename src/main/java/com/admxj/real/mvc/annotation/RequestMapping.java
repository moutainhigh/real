package com.admxj.real.mvc.annotation;

import com.admxj.real.mvc.model.enums.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jin.xiang
 * @version Id: RequestMapping, v 0.1 2019-09-27 11:20 jin.xiang Exp $
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

    String value() default "";

    RequestMethod method() default RequestMethod.GET;
}
