package com.star.meta;

import com.google.inject.Inject;

import java.util.concurrent.atomic.AtomicLong;

public final class TimeStamps {

    private static volatile AtomicLong[] lastTimeStamps = new AtomicLong[4];

    @Inject
    private InitTimeStampFactory initTimeStampFactory;

    public long getTimeStamp(int index) {
        // 初始化lastTimeStamp, 使用双检锁
        initLastTimeStamp(index);
        AtomicLong lastTimeStamp = lastTimeStamps[index];
        long currentTimeMillis = System.currentTimeMillis();
        long lastTimeMillis = lastTimeStamp.get();
        // 当前时间大于lastTimeStamp，设置lastTimeStamp为当前时间并返回当前时间，否则直接取lastTimeStamp
        return currentTimeMillis > lastTimeMillis ? cas(lastTimeStamp, lastTimeMillis, currentTimeMillis) : lastTimeMillis;
    }

    /**
     * CAS递归设置newValue
     *
     * @param lastTimeStamp
     * @param expectedValue
     * @param newValue
     * @return max(lastTimeStamp.get (), newValue)
     */
    private long cas(AtomicLong lastTimeStamp, long expectedValue, long newValue) {
        // CAS成功，直接返回newValue
        if (lastTimeStamp.compareAndSet(expectedValue, newValue)) {
            return newValue;
        } else {
            // CAS失败，检查最新值
            long currentLastTimeMillis = lastTimeStamp.get();
            // 如果最新值 >= newValue，则取最新值，不再CAS
            if (currentLastTimeMillis >= newValue) {
                return currentLastTimeMillis;
            } else {
                // 如果最新值 < newValue，则更新expectedValue为最新值，再次进行CAS
                return cas(lastTimeStamp, currentLastTimeMillis, newValue);
            }
        }
    }

    private void initLastTimeStamp(int index) {
        if (lastTimeStamps[index] == null) {
            synchronized (this) {
                if (lastTimeStamps[index] == null) {
                    // 远程获取最后的时间戳
                    long initTimeStamp = initTimeStampFactory.getTimeStamp(index);
                    if (initTimeStamp == 0) initTimeStamp = System.currentTimeMillis();
                    AtomicLong atomicLong = new AtomicLong(initTimeStamp);
                    lastTimeStamps[index] = atomicLong;
                }
            }
        }
    }
}
