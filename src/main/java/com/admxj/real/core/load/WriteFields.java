package com.admxj.real.core.load;

import com.admxj.real.core.annotation.RealValue;
import com.admxj.real.core.annotation.Resource;
import com.admxj.real.core.model.RealBeanModel;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author jin.xiang
 * @version Id: WriteFields, v 0.1 2019-09-27 15:55 jin.xiang Exp $
 */
public class WriteFields {

    public static void writeFields(Class cls, Object obj, Map<String, RealBeanModel> marsBeanObjects) throws Exception {

        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            Resource resource = field.getAnnotation(Resource.class);
            RealValue realValue = field.getAnnotation(RealValue.class);
            if (resource != null && null != realValue) {
                throw new Exception("属性不可以同时有Resource和MarsValue注解: " + cls.getName() + "#" + field.getName());
            }
            if (resource != null) {
                field.setAccessible(true);
                String resourceName = getResourceName(resource, field);
                RealBeanModel realBeanModel = marsBeanObjects.get(resourceName);
                if (null != realBeanModel) {
                    field.set(obj, realBeanModel.getObj());
                } else {
                    throw new Exception("不存在name为" + resourceName + "的RealBean");
                }
            }

            if (realValue != null) {

            }
        }
    }


    /**
     * 获取字段名
     *
     * @param resource 资源
     * @param f        字段
     * @return 名称
     */
    private static String getResourceName(Resource resource, Field f) {
        String filedName = resource.value();
        if (filedName == null || filedName.equals("")) {
            filedName = f.getName();
        }
        return filedName;
    }

}
