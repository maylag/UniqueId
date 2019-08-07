package com.star.meta;

import com.star.constant.MetaConstants;

import java.util.Random;

public class RandomMachineIdFactory implements MachineIdFactory{

    private long machineId = new Random().nextInt((int) MetaConstants.MAX_MACHINE_ID);

    @Override
    public long getMachineId() {
        return machineId;
    }
}
