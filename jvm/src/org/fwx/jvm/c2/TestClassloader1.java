package org.fwx.jvm.c2;

import sun.misc.Launcher;
import sun.util.cldr.CLDRLocaleDataMetaInfo;

import java.net.URL;
import java.security.Provider;
import java.util.Map;
import java.util.Properties;

public class TestClassloader1 {
    public static void main(String[] args) {

        /**
         * 引导类加载器
         */
        // bootstarp 加载器，加载的路径
        /*URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL);
        }

        // file:/D:/2-develop/java/jdk1.8.0_151/jre/lib/jsse.jar 中的 Provider 类
        System.out.println(Provider.class.getClassLoader());*/

        /**
         * 扩展类加载器
         */
        // 输出所有的系统参数
        Properties properties = System.getProperties();
        for (Map.Entry<Object, Object> objectEntry : properties.entrySet()) {
            System.out.println(objectEntry.getKey() +":"+objectEntry.getValue());
        }

        // 扩展类加载器，加载的路径
        String paths = System.getProperty("java.ext.dirs");
        for (String path : paths.split(";")){
            System.out.println(path);
         }

        // D:\2-develop\java\jdk1.8.0_151\jre\lib\ext 中的 CLDRLocaleDataMetaInfo 类
        System.out.println(CLDRLocaleDataMetaInfo.class.getClassLoader());
    }
}
