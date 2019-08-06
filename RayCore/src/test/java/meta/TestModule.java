package meta;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.star.meta.InitTimeStampFactory;
import com.star.meta.MachineIdFactory;

public class TestModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(InitTimeStampFactory.class).to(TestInitTimeStampFactory.class).in(Scopes.SINGLETON);
        binder.bind(MachineIdFactory.class).to(TestMachineIdFactory.class).in(Scopes.SINGLETON);
    }
}
