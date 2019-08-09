package com.star.support.redis;


import com.star.config.RayConfig;
import com.star.util.StringUtil;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;

import java.time.Duration;

public class RedisClientFactory {


    public static RedisClient getClient(int db) {
        String host = RayConfig.get(RayConfig.REDIS_HOST);
        String port = RayConfig.get(RayConfig.REDIS_PORT);
        String password = RayConfig.get(RayConfig.REDIS_PASSWORD);
        RedisURI.Builder builder = RedisURI.builder()
                .withHost(host)
                .withPort(Integer.parseInt(port))
                .withDatabase(db)
                .withTimeout(Duration.ofSeconds(30));
        if (StringUtil.isNotEmpty(password)) builder.withPassword(password);
        RedisURI redisURI = builder.build();
        return RedisClient.create(redisURI);
    }

}
