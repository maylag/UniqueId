package ioc;

import com.star.generator.IdGenerator;
import org.junit.Test;

/**
 * @program: Ray
 * @description:
 * @author: liu na
 * @create: 2019-07-21 19:24
 */
public class TestGuice {

    @Test
    public void test() {
        IdGenerator idGenerator = IdGenerator.getInstance();
        idGenerator.getId();
    }

    @Test
    public void test2(){

    }
}
