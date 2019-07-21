package com.star.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * @program: Ray
 * @description:
 * @author: liu na
 * @create: 2019-07-21 19:43
 */
public class Injectors {

    public static void inject(Object object) {
        Module module = ModuleFactory.getModule();
        Injector injector = Guice.createInjector(module);
        injector.injectMembers(object);
    }
}
