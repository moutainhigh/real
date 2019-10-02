package com.admxj.real.test.controller;

import com.admxj.real.mvc.annotation.Controller;
import com.admxj.real.mvc.annotation.RequestMapping;
import com.admxj.real.mvc.model.ModelAndView;
import com.admxj.real.mvc.model.ModelMap;

/**
 * @author jin.xiang
 * @version Id: PageController, v 0.1 2019-10-02 20:42 jin.xiang Exp $
 */
@Controller
public class PageController {

    @RequestMapping("/page")
    public ModelAndView page() {
        ModelAndView modelAndView = new ModelAndView();

        ModelMap<String, Object> model = new ModelMap();
        model.put("name", "张三");
        model.put("array", new String[] { "测试", "测试2" });

        modelAndView.setView("example");
        modelAndView.setModel(model);
        return modelAndView;
    }
}
