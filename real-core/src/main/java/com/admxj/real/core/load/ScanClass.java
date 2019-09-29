package com.admxj.real.core.load;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author jin.xiang
 * @version Id: ScanClass, v 0.1 2019-09-27 11:25 jin.xiang Exp $
 */
public class ScanClass {

    public static Set<String> loadClassList(String packageName) throws IOException {
        if (packageName == null) {
            return new LinkedHashSet<>();
        }
        return getClasses(packageName);
    }

    private static Set<String> getClasses(String packageName) {
        Set<String> classes = new LinkedHashSet<>();
        boolean recursive = true;
        String packageNameDir = packageName.replace(".", "/");

        try {
            Enumeration<URL> dir = ScanClass.class.getClassLoader().getResources(packageNameDir);

            while (dir.hasMoreElements()) {
                URL url = dir.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    findAndAddClassesInPackageByFile(filePath, packageName, recursive, classes);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return classes;
    }

    private static void findAndAddClassesInPackageByFile(String filePath, String packageName, final boolean recursive, Set<String> classes) {
        File dir = new File(filePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] dirFile = dir.listFiles(file -> (recursive && file.isDirectory()) || file.getName().endsWith(".class"));
        for (File file : dirFile) {
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(file.getAbsolutePath(), packageName + "." + file.getName(), recursive, classes);
            } else {
                String className = file.getName().substring(0, file.getName().length() - 6);
                classes.add(packageName + "." + className);
            }

        }
    }
}
