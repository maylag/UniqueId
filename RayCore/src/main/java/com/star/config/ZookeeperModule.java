package com.star.config;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.star.meta.MachineIdFactory;
import com.star.meta.ZookeeperMachineIdFactory;

/**
 * @program: Ray
 * @description:
 * @author: liu na
 * @create: 2019-07-21 19:37
 */
public class ZookeeperModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(MachineIdFactory.class).to(ZookeeperMachineIdFactory.class).in(Scopes.SINGLETON);
    }
}
