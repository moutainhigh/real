package com.admxj.real.mvc;

import com.admxj.real.mvc.annotation.Controller;
import com.admxj.real.mvc.annotation.RequestMapping;
import com.admxj.real.server.model.http.HttpRequest;
import com.admxj.real.server.model.http.HttpResponse;

import java.util.Map;

/**
 * @author jin.xiang
 * @version Id: RemoteConfigController, v 0.1 2019-09-27 09:38 jin.xiang Exp $
 */
@Controller
public class RemoteConfigController {


    @RequestMapping
    public Map<String, Object> reloadConfig(HttpRequest request, HttpResponse response) {


        return null;
    }

}
