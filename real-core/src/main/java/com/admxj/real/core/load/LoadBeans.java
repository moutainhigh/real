package com.admxj.real.core.load;

import com.admxj.real.core.annotation.RealBean;
import com.admxj.real.core.constant.RealConstant;
import com.admxj.real.core.model.RealBeanClassModel;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @author jin.xiang
 * @version Id: LoadBeans, v 0.1 2019-09-27 11:24 jin.xiang Exp $
 */
public class LoadBeans {

    private static RealSpace constants = RealSpace.getEasySpace();

    public static void loadNativeBeans() {
    }


    public static void loadBean(Class<?> cls) {
        List<RealBeanClassModel> beanList = LoadHelper.getBeanList();
        RealBeanClassModel classModel = new RealBeanClassModel();
        classModel.setClassName(cls);
        Set<Annotation> annotations = classModel.getAnnotations();
        if (annotations == null) {
            annotations = new HashSet<>();
        }
        for (Annotation annotation : cls.getAnnotations()) {
            annotations.add(annotation);
        }
        classModel.setAnnotations(annotations);
        beanList.add(classModel);

        constants.setAttr(RealConstant.REAL_BEANS, beanList);
    }
}
