package com.star.meta;

import java.util.concurrent.atomic.AtomicReference;

public final class Sequences {

    private static AtomicReference<TimeStampAndSequence>[] atomicReferences = new AtomicReference[4];

    public TimeStampAndSequence getSequence(int index, long timeStamp) {
        if (atomicReferences[index] == null) {
            synchronized (this) {
                if (atomicReferences[index] == null) {
                    TimeStampAndSequence timeStampAndSequence = new TimeStampAndSequence(timeStamp, 0);
                    AtomicReference<TimeStampAndSequence> reference = new AtomicReference<>(timeStampAndSequence);
                    atomicReferences[index] = reference;
                    return timeStampAndSequence;
                }
            }
        }
        //TODO
        return null;
    }
}
