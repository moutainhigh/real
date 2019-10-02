package com.admxj.real.mvc.resolve;

import java.util.HashMap;
import java.util.Map;

import com.admxj.real.core.constant.RealConstant;
import com.admxj.real.core.load.RealSpace;
import com.admxj.real.mvc.assembly.ProcessContextAssembly;
import com.admxj.real.mvc.assembly.impl.ProcessContextAssemblyImpl;
import com.admxj.real.mvc.context.ProcessContext;
import com.admxj.real.mvc.model.RealMappingModel;
import com.admxj.real.mvc.resolve.access.PathAccess;
import com.admxj.real.mvc.resolve.impl.ResourceProcess;
import com.admxj.real.mvc.resolve.impl.WebMvcProcess;
import com.admxj.real.server.model.http.HttpRequest;
import com.admxj.real.server.model.http.HttpResponse;
import com.admxj.real.server.util.RequestUtil;

/**
 * @author admxj
 * @version Id: ResolveRequest, v 0.1 2019-09-28 02:27 admxj Exp $
 */
public class ResolveRequest {

    private RealSpace              constants              = RealSpace.getEasySpace();

    private WebMvcProcess          webMvcProcess          = WebMvcProcess.getWebMvcProcess();
    private ResourceProcess        resourceProcess        = ResourceProcess.getResourceProcess();

    private ProcessContextAssembly processContextAssembly = new ProcessContextAssemblyImpl();

    private static ResolveRequest  resolveRequest;

    public static ResolveRequest getResolveRequest() {
        if (null == resolveRequest) {
            resolveRequest = new ResolveRequest();
        }
        return resolveRequest;
    }

    /**
     * 解析HttpRequesrt, 查找与URL对应的Controller
     * 静态资源解决思路, 优先匹配api, api不存在则匹配静态文件, 都找不到则抛出Exception
     * @param context
     * @return
     * @throws Exception
     */
    public HttpResponse resolve(ProcessContext context) throws Exception {
        HttpRequest request = context.getRequest();

        HttpResponse response = context.getResponse();

        Map<String, RealMappingModel> controllers = getControllers();
        String uri = RequestUtil.getRequestPath(request.getUriName());
        if (PathAccess.hasAccess(uri)) {
            return response;
        }
        RealMappingModel mappingModel = controllers.get(uri);
        if (null != mappingModel) {
            ProcessContext<RealMappingModel> apiProcessContext = processContextAssembly.assemble(context, mappingModel);
            webMvcProcess.process(apiProcessContext);
        } else {
            resourceProcess.process(context);
        }

        return context.getResponse();
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
