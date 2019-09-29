package com.admxj.real.base.servlet;

import com.admxj.real.server.model.http.HttpRequest;
import com.admxj.real.server.model.http.HttpResponse;

/**
 * @author admxj
 * @version Id: RealServlet, v 0.1 2019-09-28 02:00 admxj Exp $
 */
public interface RealServlet {

    /**
     * 请求接受方法
     *
     * @param request
     * @param response
     * @return obj
     */
    Object doRequest(HttpRequest request, HttpResponse response) throws Exception;

}
