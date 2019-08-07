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
        return new DemoModule();
    }
}
