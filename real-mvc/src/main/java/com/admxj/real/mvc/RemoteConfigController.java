package com.admxj.real.mvc;

import java.util.Map;

import com.admxj.real.mvc.annotation.RequestMapping;
import com.admxj.real.mvc.annotation.RestController;
import com.admxj.real.server.model.http.HttpRequest;
import com.admxj.real.server.model.http.HttpResponse;

/**
 * @author jin.xiang
 * @version Id: RemoteConfigController, v 0.1 2019-09-27 09:38 jin.xiang Exp $
 */
@RestController
public class RemoteConfigController {


    @RequestMapping
    public Map<String, Object> reloadConfig(HttpRequest request, HttpResponse response) {


        return null;
    }

}
