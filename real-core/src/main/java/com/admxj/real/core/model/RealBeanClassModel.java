package com.admxj.real.core.model;

import lombok.Data;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @author jin.xiang
 * @version Id: RealBeanClassModel, v 0.1 2019-09-27 14:25 jin.xiang Exp $
 */
@Data
public class RealBeanClassModel {

    private Class<?> className;

    private Set<Annotation> annotations;

}
