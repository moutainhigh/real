package com.admxj.real.mvc.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author jin.xiang
 * @version Id: MvcCglibProxy, v 0.1 2019-09-27 14:57 jin.xiang Exp $
 */
public class MvcCglibProxy implements MethodInterceptor {

    private Enhancer enhancer;

    private Class cls;

    public Object getProxy(Class<?> clazz) {
        this.cls = clazz;

        enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        Object object = proxy.invokeSuper(obj, args);

        return object;
    }
}
