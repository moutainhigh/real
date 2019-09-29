package com.admxj.real.ioc.load;

import com.admxj.real.core.load.LoadHelper;
import com.admxj.real.core.model.RealBeanClassModel;
import com.admxj.real.core.model.RealBeanModel;

import java.util.List;
import java.util.Map;

/**
 * @author jin.xiang
 * @version Id: LoadRealBean, v 0.1 2019-09-27 14:14 jin.xiang Exp $
 */
public class LoadRealBean {

    public static void loadBean() throws Exception {
        List<RealBeanClassModel> beanList = LoadHelper.getBeanList();

        Map<String, RealBeanModel> beanObjects = LoadHelper.getBeanObjectMap();

        for (RealBeanClassModel realBeanClassModel : beanList){

            if (false){

            }

        }
    }
}
