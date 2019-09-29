package com.admxj.real.server.util;

import com.admxj.real.server.model.http.HttpRequest;
import com.admxj.real.server.model.http.HttpResponse;

import java.lang.reflect.Method;

/**
 * @author admxj
 * @version Id: BuildParams, v 0.1 2019-09-28 15:09 admxj Exp $
 */
public class BuildParams {
    public static Object[] builder(Method method, HttpRequest request, HttpResponse response) {
        return new Object[0];
    }
}
