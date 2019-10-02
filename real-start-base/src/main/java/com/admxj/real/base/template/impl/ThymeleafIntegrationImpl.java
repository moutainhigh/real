package com.admxj.real.base.template.impl;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.Map;

/**
 * @author jin.xiang
 * @version Id: ThymeleafIntegrationImpl, v 0.1 2019-10-02 20:10 jin.xiang Exp $
 */
public class ThymeleafIntegrationImpl {

    private ThymeleafIntegrationImpl() {
    }

    public static String render(String template, Map<String, Object> model) {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        //模板所在目录，相对于当前classloader的classpath。
        resolver.setPrefix("template/");
        //模板文件后缀
        resolver.setSuffix(".html");

        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);

        Context context = new Context();
        context.setVariables(model);

        //渲染模板
        String renderResult = engine.process(template, context);
        return renderResult;

        //这个example.html 放在resources 下面.这样机会生成一个result.html文件,结果都已经放进去了.

    }
}
