package com.admxj.real.mvc.resolve;

import java.util.HashMap;
import java.util.Map;

import com.admxj.real.core.constant.RealConstant;
import com.admxj.real.core.load.RealSpace;
import com.admxj.real.mvc.model.RealMappingModel;
import com.admxj.real.mvc.resolve.access.PathAccess;
import com.admxj.real.server.model.http.HttpRequest;
import com.admxj.real.server.model.http.HttpResponse;
import com.admxj.real.server.util.RequestUtil;

/**
 * @author admxj
 * @version Id: ResolveRequest, v 0.1 2019-09-28 02:27 admxj Exp $
 */
public class ResolveRequest {

    private RealSpace             constants     = RealSpace.getEasySpace();

    private WebMvcProcess         webMvcProcess = WebMvcProcess.getWebMvcProcess();

    private static ResolveRequest resolveRequest;

    public static ResolveRequest getResolveRequest() {
        if (null == resolveRequest) {
            resolveRequest = new ResolveRequest();
        }
        return resolveRequest;
    }

    /**
     * 解析HttpRequesrt, 查找与URL对应的Controller
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public Object resolve(HttpRequest request, HttpResponse response) throws Exception {
        Map<String, RealMappingModel> controllers = getControllers();
        String uri = RequestUtil.getRequestPath(request.getUriName());
        if (PathAccess.hasAccess(uri)) {
            return "ok";
        }

        Object result = webMvcProcess.process(controllers.get(uri), request.getMethod(), request, response);

        return result;
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
