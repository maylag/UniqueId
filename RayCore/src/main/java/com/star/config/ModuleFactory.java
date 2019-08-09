package com.star.config;

import com.google.inject.Module;

/**
 * @program: Ray
 * @description:
 * @author: liu na
 * @create: 2019-07-21 19:39
 */
public class ModuleFactory {

    public static Module getModule() {
        String style = RayConfig.get(RayConfig.MIDDLEWARE_STYLE);
        if ("none".equalsIgnoreCase(style)) {
            return new DemoModule();
        } else if ("redis".equalsIgnoreCase(style)) {
            return new RedisModule();
        } else if ("zookeeper".equalsIgnoreCase(style)) {
            return new ZookeeperModule();
        } else {
            throw new IllegalArgumentException(style);
        }
    }
}
