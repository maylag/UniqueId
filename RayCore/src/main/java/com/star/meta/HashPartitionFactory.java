package com.star.meta;

import static com.star.constant.CommonConstant.MAX_DOMAIN_ID;

/**
 * @program: Ray
 * @description:
 * @author: liu na
 * @create: 2019-07-25 10:56
 */
public class HashPartitionFactory implements PartitionFactory {
    @Override
    public long getIndex(String domain) {
        int h;
        return ((h = domain.hashCode()) ^ (h >>> 16)) & MAX_DOMAIN_ID;
    }
}
