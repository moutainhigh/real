package com.admxj.real.mvc.resolve;

import com.admxj.real.core.constant.RealConstant;
import com.admxj.real.core.load.RealSpace;
import com.admxj.real.mvc.model.RealMappingModel;
import com.admxj.real.mvc.resolve.access.PathAccess;
import com.admxj.real.server.model.http.HttpRequest;
import com.admxj.real.server.model.http.HttpResponse;
import com.admxj.real.server.util.BuildParams;
import com.admxj.real.server.util.RequestUtil;
import io.netty.handler.codec.http.HttpMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author admxj
 * @version Id: ResolveRequest, v 0.1 2019-09-28 02:27 admxj Exp $
 */
public class ResolveRequest {

    private RealSpace constants = RealSpace.getEasySpace();

    private static ResolveRequest resolveRequest;

    public static ResolveRequest getResolveRequest() {
        if (null == resolveRequest) {
            resolveRequest = new ResolveRequest();
        }
        return resolveRequest;
    }

    public Object resolve(HttpRequest request, HttpResponse response) throws Exception {
        Map<String, RealMappingModel> controllers = getControllers();
        String uri = RequestUtil.getRequestPath(request.getUriName());
        if (PathAccess.hasAccess(uri)) {
            return "ok";
        }

        Object result = execute(controllers.get(uri), request.getMethod(), request, response);

        return result;
    }


    private Object execute(RealMappingModel model, HttpMethod method, HttpRequest request, HttpResponse response) throws Exception {
        if (null == model) {
            throw new Exception("没有对应API的接口: " + request.getUriName());
        }
        String reqMethodName = method.name().toLowerCase();
        String modelMethod = model.getRequestMethod().name().toLowerCase();
        if (modelMethod.equals(reqMethodName)) {

            // TODO 处理拦截器
            executeInterceptors();

            Object result = executeControllerMethod(model, request, response);

            return result;
        }else {
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

    }

    private Map<String, RealMappingModel> getControllers() {
        Map<String, RealMappingModel> controlObjects = new HashMap<>();
        Object obj = constants.getAttr(RealConstant.CONTROLLER_OBJECTS);
        if (null != obj) {
            controlObjects = (Map<String, RealMappingModel>) obj;
        }
        return controlObjects;
    }
}
