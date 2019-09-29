package com.admxj.real.base.impl;

import com.admxj.real.base.StartMap;
import com.admxj.real.core.StartParam;
import com.admxj.real.core.annotation.BeanScan;
import com.admxj.real.core.constant.RealConstant;
import com.admxj.real.core.load.RealSpace;

import java.util.HashSet;
import java.util.Set;

/**
 * @author admxj
 * @version Id: StartConfig, v 0.1 2019-09-27 02:05 admxj Exp $
 */
public class StartConfig implements StartMap {

    /**
     * 获取全局存储空间
     */
    private RealSpace constants = RealSpace.getEasySpace();

    @Override
    public void load(StartParam startParam) throws Exception {

        loadBeanScan(startParam);

    }


    private void loadBeanScan(StartParam startParam) {
        Set<String> scanPackage = new HashSet<>();
        scanPackage.add(startParam.getClazz().getName());
        Class<?> clazz = startParam.getClazz();
        BeanScan beanScan = clazz.getAnnotation(BeanScan.class);
        if (null != beanScan) {
            String[] value = beanScan.value();
            for (String packageName : value) {
                scanPackage.add(packageName);
            }
        }


        constants.setAttr(RealConstant.SCAN_PACKAGE, scanPackage);
    }
}
