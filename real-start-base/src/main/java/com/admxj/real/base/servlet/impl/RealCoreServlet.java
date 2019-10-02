package com.admxj.real.base.servlet.impl;

import com.admxj.real.core.constant.DataType;
import com.admxj.real.mvc.model.ModelAndView;
import com.admxj.real.mvc.resolve.ResolveRequest;
import com.admxj.real.server.model.http.HttpRequest;
import com.admxj.real.server.model.http.HttpResponse;
import com.admxj.real.server.servlet.RealServlet;
import com.alibaba.fastjson.JSON;
import io.netty.handler.codec.http.HttpHeaderNames;

/**
 * @author admxj
 * @version Id: RealCoreServlet, v 0.1 2019-09-28 02:01 admxj Exp $
 */

public class RealCoreServlet implements RealServlet {

    @Override
    public void doRequest(HttpRequest request, HttpResponse response) throws Exception {

        try {

            ResolveRequest resolveRequest = ResolveRequest.getResolveRequest();
            Object result = resolveRequest.resolve(request, response);

            if (isNotObject(result)) {
                response.setBody(String.valueOf(result));
            } else if (result instanceof ModelAndView) {
                // TODO: 2019-10-02 jin.xiang 返回模板引擎
            } else {
                response.addHeader(HttpHeaderNames.CONTENT_TYPE.toString(), "text/json; charset=UTF-8");
                response.setBody(JSON.toJSONString(result));
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
