package com.admxj.real.mvc.servlet.impl;

import com.admxj.real.mvc.context.ProcessContext;
import com.admxj.real.mvc.resolve.ResolveRequest;
import com.admxj.real.mvc.resolve.impl.ResourceProcess;
import com.admxj.real.server.model.http.HttpRequest;
import com.admxj.real.server.model.http.HttpResponse;
import com.admxj.real.server.servlet.RealServlet;

/**
 * @author admxj
 * @version Id: RealCoreServlet, v 0.1 2019-09-28 02:01 admxj Exp $
 */

public class RealCoreServlet implements RealServlet {

    private ResourceProcess resourceProcess = ResourceProcess.getResourceProcess();

    private ResolveRequest  resolveRequest  = ResolveRequest.getResolveRequest();

    @Override
    public HttpResponse doRequest(HttpRequest request) throws Exception {

        HttpResponse httpResponse = new HttpResponse(request.getCtx());

        ProcessContext context = ProcessContext.builder().request(request).response(httpResponse).build();

        try {
            return resolveRequest.resolve(context);

        } catch (Exception e) {
            throw new Exception("解析请求出错", e);
        }
    }

}
