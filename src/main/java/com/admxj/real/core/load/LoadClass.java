package com.admxj.real.core.load;

import com.admxj.real.core.constant.RealSpace;
import com.admxj.real.mvc.annotation.Controller;
import com.admxj.real.core.annotation.RealBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * @author admxj
 * @version Id: LoadClass, v 0.1 2019-09-27 03:03 admxj Exp $
 */
public class LoadClass {

    private final static Logger logger = LoggerFactory.getLogger(LoadClass.class);

    /**
     * 获取全局存储空间
     */
    private RealSpace constants = RealSpace.getEasySpace();

    /**
     * 加载所有Bean, 包含controller
     *
     * @param packageName 带扫描包名
     */
    public static void loadBeans(String packageName) throws Exception {
        /* 加载本地bean */
        LoadBeans.loadNativeBeans();

        loadAllBeans(packageName);
    }

    private static void loadAllBeans(String packageName) throws Exception {
        try {
            Set<String> strings = ScanClass.loadClassList(packageName);
            for (String string : strings) {
                Class<?> clazz = Class.forName(string);
                Controller controller = clazz.getAnnotation(Controller.class);
                RealBean realBean = clazz.getAnnotation(RealBean.class);
                if (controller != null) {
                    LoadBeans.loadController(clazz, controller);
                }
                if (realBean != null) {
                    LoadBeans.loadEasyBean(clazz, realBean);
                }

            }
        } catch (Exception e) {
            String errorMessage = "扫描[" + packageName + "]包下的类发生错误";
            logger.error(errorMessage);
            throw new Exception(errorMessage);
        }
    }
}
