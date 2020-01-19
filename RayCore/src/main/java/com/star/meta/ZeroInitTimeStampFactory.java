package com.star.meta;

public class ZeroInitTimeStampFactory implements InitTimeStampFactory{
    @Override
    public long getTimeStamp() {
        return 0;
    }
}
