package com.admxj.real.core.load;

import com.admxj.real.core.constant.RealConstant;
import com.admxj.real.core.model.RealBeanClassModel;
import com.admxj.real.core.model.RealBeanModel;

import java.util.*;

/**
 * @author jin.xiang
 * @version Id: LoadHelper, v 0.1 2019-09-27 14:22 jin.xiang Exp $
 */
public class LoadHelper {

    private static RealSpace constants = RealSpace.getEasySpace();

    public static List<RealBeanClassModel> getBeanList() {
        List<RealBeanClassModel> realBeansList = new ArrayList<>();

        Object realBeans = constants.getAttr(RealConstant.REAL_BEANS);
        if (realBeans != null) {
            realBeansList = (List<RealBeanClassModel>) realBeans;
        }
        return realBeansList;
    }

    public static List<RealBeanClassModel> getControllerList() {
        List<RealBeanClassModel> realBeansList = new ArrayList<>();

        Object reamBeans = constants.getAttr(RealConstant.CONTROLLERS);
        if (reamBeans != null) {
            realBeansList = (List<RealBeanClassModel>) reamBeans;
        }
        return realBeansList;
    }

    public static Map<String, RealBeanModel> getBeanObjectMap() {
        Map<String, RealBeanModel> realBeans = new HashMap<>();

        Object object = constants.getAttr(RealConstant.REAL_BEAN_OBJECTS);
        if (object != null) {
            realBeans = (Map<String, RealBeanModel>) object;
        }
        return realBeans;
    }

    public static Set<String> getScanPackage(){
        Set<String> scanPackage = new HashSet<>();
        Object object = constants.getAttr(RealConstant.SCAN_PACKAGE);
        if (null != object){
            scanPackage = (Set<String>) object;
        }
        return scanPackage;
    }
}
