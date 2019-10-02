package com.admxj.real.mvc.template;

import java.util.Map;

/**
 * @author jin.xiang
 * @version Id: TemplateIntegration, v 0.1 2019-10-02 20:16 jin.xiang Exp $
 */
public interface TemplateIntegration {

    public String render(String template, Map<String, Object> model);

}
