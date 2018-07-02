package com.ly.zmn48644.thunder.extension;

import com.ly.zmn48644.thunder.common.ThunderConstants;

import com.ly.zmn48644.thunder.registry.RegistryFactory;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者：张明楠
 * 时间：2018/7/2
 */
public class ExtensionLoader<T> {

    private static final String PREFIX = "META-INF/thunder/";

    private ClassLoader classLoader;

    private Class<T> type;

    private static Map<Class<?>, ExtensionLoader<?>> extensionLoaderMap = new HashMap<>();

    public ExtensionLoader(Class<T> type) {
        this.type = type;
        classLoader = Thread.currentThread().getContextClassLoader();
    }

    public static void main(String[] args) {
        RegistryFactory zookeeper = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getExtension("zookeeper");
        System.out.println(zookeeper);
    }

    public static <T> ExtensionLoader<T> getExtensionLoader(Class<T> type) {
        ExtensionLoader<T> loader = (ExtensionLoader<T>) extensionLoaderMap.get(type);
        if (loader == null) {
            loader = new ExtensionLoader(type);
        }
        return loader;
    }


    public T getExtension(String name) {
        String fullPaht = PREFIX + type.getName();
        try {

            Enumeration<URL> resources = classLoader.getResources(fullPaht);

            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                InputStream inputStream = url.openStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, ThunderConstants.DEFAULT_CHARACTER));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    line = StringUtils.trim(line);
                    Class<T> clazz = (Class<T>) Class.forName(line, true, classLoader);
                    String spiName = getSpiName(clazz);
                    if (spiName.equals(name)) {
                        return clazz.newInstance();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getSpiName(Class<?> clazz) {
        SpiMeta spiMeta = clazz.getAnnotation(SpiMeta.class);
        String name = spiMeta.name();
        name = StringUtils.isNotBlank(name) ? name : clazz.getSimpleName();
        return name;
    }

}
