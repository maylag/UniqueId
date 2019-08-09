package redis;

import com.star.support.redis.RedisConnections;
import io.lettuce.core.api.StatefulRedisConnection;
import org.junit.Test;

public class TestRedis {

    @Test
    public void test() {
        StatefulRedisConnection<String, String> connection = RedisConnections.getConnection(1);
        String ray = connection.sync().set("ray", "1");
        System.out.println(ray);
    }
}
