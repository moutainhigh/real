package com.admxj.real.test.controller;

import com.admxj.real.mvc.annotation.Controller;
import com.admxj.real.mvc.annotation.RequestMapping;
import com.admxj.real.mvc.annotation.RestController;

/**
 * @author admxj
 * @version Id: UserController, v 0.1 2019-09-28 15:13 admxj Exp $
 */
@RestController
public class UserController {

    @RequestMapping("/user")
    public String index(){

        return "index";
    }
}
