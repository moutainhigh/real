package com.admxj.real.mvc.resolve.access;

/**
 * 配置哪些url不处理
 *
 * @author admxj
 * @version Id: PathAccess, v 0.1 2019-09-28 13:39 admxj Exp $
 */

import java.util.HashMap;
import java.util.Map;

public class PathAccess {

    private static Map<String, String> urls;

    /**
     * 加载配置
     */
    private static void init() {
        if (urls == null) {
            urls = new HashMap<>();
            urls.put("favicon.ico", "no");
        }
    }

    /**
     * 判断是否处理
     * @param uri
     * @return
     */
    public static Boolean hasAccess(String uri) {
        init();
        return urls.get(uri) != null;
    }
}
