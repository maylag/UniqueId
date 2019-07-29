package com.star.meta;

import static com.star.constant.MetaConstants.MAX_DOMAIN_ID;

public final class Partitions {

    private Partitions() {
    }

    public static long getIndex(String domain) {
        int h;
        return ((h = domain.hashCode()) ^ (h >>> 16)) & MAX_DOMAIN_ID;
    }
}
