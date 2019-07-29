package com.star.meta;

import com.star.constant.MetaConstants;

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
        AtomicReference<TimeStampAndSequence> atomicReference = atomicReferences[index];
        TimeStampAndSequence timeStampAndSequence = atomicReference.get();
        TimeStampAndSequence newTimeStampAndSequence = buildNewTimeStampAndSequence(timeStamp, timeStampAndSequence);
        if (atomicReference.compareAndSet(timeStampAndSequence, newTimeStampAndSequence)) {
            return newTimeStampAndSequence;
        } else {
            return getSequence(index, timeStamp);
        }

        // 之后需要同步timeStamp
    }

    private TimeStampAndSequence buildNewTimeStampAndSequence(long timeStamp, TimeStampAndSequence timeStampAndSequence) {
        if (timeStamp == timeStampAndSequence.getTimestamp()) {
            return buildAppendTimeStampAndSequence(timeStamp, timeStampAndSequence);
        } else if (timeStamp > timeStampAndSequence.getTimestamp()) {
            return new TimeStampAndSequence(timeStamp, 0);
        } else {
            return buildAppendTimeStampAndSequence(timeStampAndSequence.getTimestamp(), timeStampAndSequence);
        }
    }

    private TimeStampAndSequence buildAppendTimeStampAndSequence(long timeStamp, TimeStampAndSequence timeStampAndSequence) {
        long currentSequence = timeStampAndSequence.getSequence() + 1;
        return currentSequence > MetaConstants.SEQUENCE_MASK
                ? new TimeStampAndSequence(timeStamp + 1, 0)
                : new TimeStampAndSequence(timeStamp, currentSequence);
    }
}
