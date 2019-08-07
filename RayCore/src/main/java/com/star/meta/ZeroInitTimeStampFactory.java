package com.star.meta;

public class ZeroInitTimeStampFactory implements InitTimeStampFactory{
    @Override
    public long getTimeStamp(long index) {
        return 0;
    }
}
