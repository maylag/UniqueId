package meta;

import com.star.meta.MachineIdFactory;

public class TestMachineIdFactory implements MachineIdFactory {
    @Override
    public long getMachineId() {
        return 1;
    }
}
