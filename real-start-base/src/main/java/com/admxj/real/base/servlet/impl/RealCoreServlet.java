package com.admxj.real.base.servlet.impl;

import com.admxj.real.base.servlet.RealServlet;
import com.admxj.real.core.constant.DataType;
import com.admxj.real.server.model.http.HttpRequest;
import com.admxj.real.server.model.http.HttpResponse;
import com.admxj.real.server.resolve.ResolveRequest;
import com.alibaba.fastjson.JSON;

/**
 * @author admxj
 * @version Id: RealCoreServlet, v 0.1 2019-09-28 02:01 admxj Exp $
 */

public class RealCoreServlet implements RealServlet {

    @Override
    public Object doRequest(HttpRequest request, HttpResponse response) throws Exception {

        try {

            ResolveRequest resolveRequest = ResolveRequest.getResolveRequest();
            Object result = resolveRequest.resolve(request, response);

            if (isNotObject(result)) {
                return resolveRequest;
            } else {
                return JSON.toJSONString(request);
            }
        } catch (Exception e) {
            throw new Exception("解析请求出错", e);
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


}
