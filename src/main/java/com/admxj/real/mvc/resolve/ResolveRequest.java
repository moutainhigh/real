package com.admxj.real.mvc.resolve;

/**
 * @author admxj
 * @version Id: ResolveRequest, v 0.1 2019-09-28 02:27 admxj Exp $
 */
public class ResolveRequest {

    private static ResolveRequest resolveRequest;

    public static ResolveRequest getResolveRequest() {
        if (null == resolveRequest) {
            resolveRequest = new ResolveRequest();
        }
        return resolveRequest;
    }

}
