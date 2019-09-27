package com.admxj.real.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jin.xiang
 * @version Id: RealBean, v 0.1 2019-09-27 14:18 jin.xiang Exp $
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RealBean {
}
