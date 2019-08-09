package com.star.config;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.star.meta.InitTimeStampFactory;
import com.star.meta.MachineIdFactory;
import com.star.meta.RedisInitTimeStampFactory;
import com.star.meta.RedisMachineIdFactory;

/**
 * @program: Ray
 * @description:
 * @author: liu na
 * @create: 2019-07-21 19:25
 */
public class RedisModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(MachineIdFactory.class).to(RedisMachineIdFactory.class).in(Scopes.SINGLETON);
        binder.bind(InitTimeStampFactory.class).to(RedisInitTimeStampFactory.class).in(Scopes.SINGLETON);
    }
}
