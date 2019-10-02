package com.admxj.real.mvc.resolve;

import com.admxj.real.mvc.model.RealMappingModel;
import com.admxj.real.server.model.http.HttpRequest;
import com.admxj.real.server.model.http.HttpResponse;
import com.admxj.real.server.util.BuildParams;
import io.netty.handler.codec.http.HttpMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jin.xiang
 * @version Id: WebMvcProcess, v 0.1 2019-10-02 16:42 jin.xiang Exp $
 */
public class WebMvcProcess {

    private static WebMvcProcess webMvcProcess;

    public static WebMvcProcess getWebMvcProcess() {
        if (null == webMvcProcess) {
            new WebMvcProcess();
        }
        return webMvcProcess;
    }

    private WebMvcProcess() {
    }

    public Object process(RealMappingModel model, HttpMethod method, HttpRequest request, HttpResponse response) throws Exception {
        if (null == model) {
            throw new Exception("没有对应API的接口: " + request.getUriName());
        }
        String reqMethodName = method.name().toLowerCase();
        String modelMethod = model.getRequestMethod().name().toLowerCase();
        if (modelMethod.equals(reqMethodName)) {

            executeInterceptors();

            Object result = executeControllerMethod(model, request, response);
            Annotation controller = model.getAnnotation();
            if (controller == null) {
                throw new Exception("找不到 Controller/RestController");
            }

            return result;
        } else {
            /* 如果请求方式和controller的映射不一致，则提示客户端 */
            throw new Exception("此接口的请求方式为[" + reqMethodName + "]");
        }
    }

    private Object executeControllerMethod(RealMappingModel model, HttpRequest request, HttpResponse response) throws InvocationTargetException, IllegalAccessException {

        Object object = model.getObject();
        Method method = getMethod(model);

        Object[] params = BuildParams.builder(method, request, response);

        Object result = method.invoke(object, params);

        return result;
    }

    private Method getMethod(RealMappingModel model) {
        Method[] methods = model.getCls().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(model.getMethod())) {
                return method;
            }
        }
        return null;
    }

    private void executeInterceptors() {
        // TODO 处理拦截器
    }

}
