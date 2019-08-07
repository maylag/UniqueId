package com.star.config;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.star.meta.InitTimeStampFactory;
import com.star.meta.MachineIdFactory;
import com.star.meta.RandomMachineIdFactory;
import com.star.meta.ZeroInitTimeStampFactory;

public class DemoModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(InitTimeStampFactory.class).to(ZeroInitTimeStampFactory.class).in(Scopes.SINGLETON);
        binder.bind(MachineIdFactory.class).to(RandomMachineIdFactory.class).in(Scopes.SINGLETON);
    }
}
