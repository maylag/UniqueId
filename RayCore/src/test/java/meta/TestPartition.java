package meta;

import com.star.meta.Partitions;
import org.junit.Test;

public class TestPartition {

    @Test
    public void testRight() {
        int i = Partitions.class.hashCode();
        System.out.println(i >>> 16);
    }

    @Test
    public void testPartition() {
        String domain1 = "user";
        String domain2 = Partitions.class.getName();
        System.out.println(Partitions.getIndex(domain1));
        System.out.println(Partitions.getIndex(domain2));

        for (int i = 0; i < 1000; i++) {
            String domain = "domain" + i;
            long index = Partitions.getIndex(domain);
            System.out.println(index);
            if (index > 3) throw new IllegalStateException();
        }
    }
}
