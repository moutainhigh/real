package com.admxj.real.base.servlet.impl;

import com.admxj.real.base.servlet.RealServlet;
import com.admxj.real.mvc.resolve.ResolveRequest;
import com.admxj.real.server.HttpRequest;
import com.admxj.real.server.HttpResponse;

/**
 * @author admxj
 * @version Id: RealCoreServlet, v 0.1 2019-09-28 02:01 admxj Exp $
 */

public class RealCoreServlet implements RealServlet {
    @Override
    public Object doRequest(HttpRequest request, HttpResponse response) throws Exception {

        ResolveRequest resolveRequest = ResolveRequest.getResolveRequest();

        // TODO: 2019-09-28 admxj
        return null;
    }

    public Object resolve(HttpRequest request, HttpResponse response) throws Exception {

        return null;
    }
}
