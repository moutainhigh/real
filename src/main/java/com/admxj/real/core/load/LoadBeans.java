package com.admxj.real.core.load;

import com.admxj.real.core.constant.RealConstant;
import com.admxj.real.core.constant.RealSpace;
import com.admxj.real.core.model.RealBeanClassModel;
import com.admxj.real.mvc.annotation.Controller;
import com.admxj.real.core.annotation.RealBean;

import java.util.List;

/**
 * @author jin.xiang
 * @version Id: LoadBeans, v 0.1 2019-09-27 11:24 jin.xiang Exp $
 */
public class LoadBeans {

    private static RealSpace constants = RealSpace.getEasySpace();

    public static void loadNativeBeans() {
    }

    public static void loadController(Class<?> cls, Controller controller) {
        List<RealBeanClassModel> beanList = LoadHelper.getControllerList();

        RealBeanClassModel classModel = new RealBeanClassModel();
        classModel.setClassName(cls);
        classModel.setAnnotation(controller);

        beanList.add(classModel);
        constants.setAttr(RealConstant.CONTROLLERS, beanList);
    }

    public static void loadEasyBean(Class<?> cls, RealBean realBean) {
        List<RealBeanClassModel> beanList = LoadHelper.getBeanList();

        RealBeanClassModel classModel = new RealBeanClassModel();
        classModel.setClassName(cls);
        classModel.setAnnotation(realBean);

        beanList.add(classModel);

        constants.setAttr(RealConstant.REAL_BEANS, realBean);

    }
}
