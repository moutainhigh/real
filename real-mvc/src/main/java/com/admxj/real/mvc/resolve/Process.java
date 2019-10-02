package com.admxj.real.mvc.resolve;

import com.admxj.real.server.model.http.HttpRequest;
import com.admxj.real.server.model.http.HttpResponse;

/**
 * @author jin.xiang
 * @version Id: Process, v 0.1 2019-10-03 01:20 jin.xiang Exp $
 */
public interface Process {

    public void process(HttpRequest request, HttpResponse response) throws Exception;

}
