package com.admxj.real.base.impl;

import com.admxj.real.core.StartMap;
import com.admxj.real.core.StartParam;
import com.admxj.real.core.constant.Symbol;
import com.admxj.real.core.context.LoadClass;
import com.admxj.real.core.load.RealSpace;

/**
 * @author admxj
 * @version Id: StartBeans, v 0.1 2019-09-27 02:05 admxj Exp $
 */
public class StartBeans implements StartMap {

    /**
     * 获取全局存储空间
     */
    private RealSpace constants = RealSpace.getEasySpace();

    @Override
    public void load(StartParam startParam) throws Exception {
        String packageName = getPackageName(startParam);

        constants.setAttr("rootPath", packageName);

        LoadClass.loadBeans(packageName);

    }

    private String getPackageName(StartParam startParam) throws Exception {
        String className = startParam.getClazz().getName();
        String packageName = null;
        if (className.lastIndexOf(Symbol.POINT) > 0) {
            packageName = className.substring(0, className.lastIndexOf(Symbol.POINT));
        }
        if (null == packageName || packageName.length() == 0) {
            throw new Exception("启动服务的main方法所在的类, 必须在放在包下面");
        }
        return packageName;
    }
}
