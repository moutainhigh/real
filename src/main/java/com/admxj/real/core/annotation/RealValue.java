package com.admxj.real.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jin.xiang
 * @version Id: RealValue, v 0.1 2019-09-27 16:00 jin.xiang Exp $
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RealValue {
}
