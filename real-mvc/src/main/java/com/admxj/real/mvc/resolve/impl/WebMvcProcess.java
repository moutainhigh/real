package com.admxj.real.mvc.resolve.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.admxj.real.core.constant.DataType;
import com.admxj.real.mvc.context.ProcessContext;
import com.admxj.real.mvc.model.ModelAndView;
import com.admxj.real.mvc.model.RealMappingModel;
import com.admxj.real.mvc.template.impl.ThymeleafIntegrationImpl;
import com.admxj.real.server.model.http.HttpRequest;
import com.admxj.real.server.model.http.HttpResponse;
import com.admxj.real.server.util.BuildParams;
import com.alibaba.fastjson.JSON;

import io.netty.handler.codec.http.HttpHeaderNames;

/**
 * @author jin.xiang
 * @version Id: WebMvcProcess, v 0.1 2019-10-02 16:42 jin.xiang Exp $
 */
public class WebMvcProcess {

    private static WebMvcProcess webMvcProcess;

    public static WebMvcProcess getWebMvcProcess() {
        if (null == webMvcProcess) {
            webMvcProcess = new WebMvcProcess();
        }
        return webMvcProcess;
    }

    private WebMvcProcess() {
    }

    public void process(ProcessContext<RealMappingModel> context) throws Exception {

        HttpRequest request = context.getRequest();
        HttpResponse response = context.getResponse();
        RealMappingModel model = context.getPayload();

        String reqMethodName = request.getMethod().name().toLowerCase();
        String modelMethod = model.getRequestMethod().name().toLowerCase();
        if (modelMethod.equals(reqMethodName)) {
            Annotation controller = model.getAnnotation();
            if (controller == null) {
                throw new Exception(model.getCls().getName() + "找不到 Controller/RestController");
            }
            executeInterceptors();

            Object result = executeControllerMethod(model, request, response);

            buildResponse(response, result);
        } else {
            /* 如果请求方式和controller的映射不一致，则提示客户端 */
            throw new Exception("此接口的请求方式为[" + reqMethodName + "]");
        }
    }

    private void buildResponse(HttpResponse response, Object result) {

        if (isNotObject(result)) {
            response.setBody(String.valueOf(result));
        } else if (result instanceof ModelAndView) {
            ModelAndView view = (ModelAndView) result;
            String renderContent = ThymeleafIntegrationImpl.render(view.getView(), view.getModel());
            response.addHeader(HttpHeaderNames.CONTENT_TYPE.toString(), "text/html; charset=UTF-8");
            response.setBody(renderContent);
        } else {
            response.addHeader(HttpHeaderNames.CONTENT_TYPE.toString(), "text/json; charset=UTF-8");
            response.setBody(JSON.toJSONString(result));
        }
    }

    private boolean isNotObject(Object result) {
        String fieldTypeName = result.getClass().getSimpleName().toUpperCase();
        switch (fieldTypeName) {
            case DataType.INT:
            case DataType.INTEGER:
            case DataType.BYTE:
            case DataType.STRING:
            case DataType.CHAR:
            case DataType.CHARACTER:
            case DataType.DOUBLE:
            case DataType.FLOAT:
            case DataType.LONG:
            case DataType.SHORT:
            case DataType.BOOLEAN:
                return true;
            default:
                return false;
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
