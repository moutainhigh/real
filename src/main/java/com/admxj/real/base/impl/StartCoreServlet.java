package com.admxj.real.base.impl;

import com.admxj.real.base.StartMap;
import com.admxj.real.base.StartParam;
import com.admxj.real.base.servlet.impl.RealCoreServlet;
import com.admxj.real.core.constant.RealSpace;

/**
 * @author admxj
 * @version Id: StartCoreServlet, v 0.1 2019-09-27 02:04 admxj Exp $
 */
public class StartCoreServlet implements StartMap {


    /**
     * 获取全局存储空间
     */
    private RealSpace constants = RealSpace.getEasySpace();

    @Override
    public void load(StartParam startParam) throws Exception {

        constants.setAttr("core", RealCoreServlet.class.getName());

    }
}
