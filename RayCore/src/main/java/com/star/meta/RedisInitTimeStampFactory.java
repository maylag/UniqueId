package com.star.meta;

import com.star.support.redis.RedisConnections;
import com.star.util.TimerUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class RedisInitTimeStampFactory implements InitTimeStampFactory {

    private static final String LAST_TIME = "last:time";

    public RedisInitTimeStampFactory() {
        TimerUtil.schedule(() -> {
            AtomicReference<TimeStampAndSequence>[] atomicReferences = TimeAndSequences.getAtomicReferences();
            Long maxTimeStamp = Arrays.stream(atomicReferences)
                    .filter(Objects::nonNull)
                    .map(AtomicReference::get)
                    .map(TimeStampAndSequence::getTimestamp)
                    .max(Comparator.comparing(x -> x))
                    .orElse(0L);
            String remoteValue = RedisConnections.getConnection(6).sync().get(LAST_TIME);
            long remoteTimeStamp = parseLong(remoteValue);
            if (maxTimeStamp > remoteTimeStamp) {
                RedisConnections.getConnection(6).sync().set(LAST_TIME, Objects.toString(maxTimeStamp));
            }
        }, 0, 60 * 1000);
    }

    @Override
    public long getTimeStamp(long index) {
        String value = RedisConnections.getConnection(6).sync().get(LAST_TIME);
        return parseLong(value);
    }

    private long parseLong(String value) {
        return value == null ? 0 : Long.parseLong(value);
    }
}
