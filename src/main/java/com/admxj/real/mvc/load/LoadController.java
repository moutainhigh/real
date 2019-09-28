package com.admxj.real.mvc.load;

import com.admxj.real.core.constant.RealConstant;
import com.admxj.real.core.constant.RealSpace;
import com.admxj.real.core.load.LoadHelper;
import com.admxj.real.core.load.WriteFields;
import com.admxj.real.core.model.RealBeanClassModel;
import com.admxj.real.core.model.RealBeanModel;
import com.admxj.real.mvc.annotation.RequestMapping;
import com.admxj.real.mvc.model.RealMappingModel;
import com.admxj.real.mvc.proxy.MvcCglibProxy;
import com.admxj.real.server.util.RequestUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jin.xiang
 * @version Id: LoadController, v 0.1 2019-09-27 14:44 jin.xiang Exp $
 */
public class LoadController {

    private static RealSpace constants = RealSpace.getEasySpace();

    /**
     * 加载所有的 Controller
     * @throws Exception
     */
    public static void loadController() throws Exception {
        List<RealBeanClassModel> controllerList = LoadHelper.getControllerList();
        //默认controller Object对象的map大小为已知controller的两倍
        Map<String, RealMappingModel> controlObjects = new HashMap<>(controllerList.size() * 2);
        Map<String, RealBeanModel> beanObjectMap = LoadHelper.getBeanObjectMap();
        for (RealBeanClassModel model : controllerList) {
            Class<?> cls = model.getClassName();
            Object object = iocControl(cls, beanObjectMap);

            Method[] methods = cls.getMethods();
            for (Method method : methods) {
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                if (requestMapping != null) {
                    RealMappingModel mappingModel = new RealMappingModel();
                    mappingModel.setCls(cls);
                    mappingModel.setMethod(method.getName());
                    mappingModel.setObject(object);
                    mappingModel.setRequestMethod(requestMapping.method());
                    controlObjects.put(RequestUtil.getRequestPath(requestMapping.value()), mappingModel);
                }
            }

        }
        constants.setAttr(RealConstant.CONTROLLER_OBJECTS, controlObjects);

    }

    private static Object iocControl(Class<?> cls, Map<String, RealBeanModel> realBeanObjs) throws Exception {
        MvcCglibProxy mvcCglibProxy = new MvcCglibProxy();
        Object proxy = mvcCglibProxy.getProxy(cls);

        // 注入属性
        WriteFields.writeFields(cls, proxy, realBeanObjs);

        return proxy;
    }
}
