package com.star.config;

import com.star.util.PropertiesUtil;

import java.util.Properties;

public class RayConfig {

    public static final String MIDDLEWARE_STYLE = "middleware.style";
    public static final String REDIS_HOST = "redis.host";
    public static final String REDIS_PORT = "redis.port";
    public static final String REDIS_PASSWORD = "redis.password";

    private static Properties properties = PropertiesUtil.loadProperties("ray.properties");

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
