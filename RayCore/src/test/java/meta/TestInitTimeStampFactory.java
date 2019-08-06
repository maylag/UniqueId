package meta;

import com.star.meta.InitTimeStampFactory;

public class TestInitTimeStampFactory implements InitTimeStampFactory {
    @Override
    public long getTimeStamp(long index) {
        return 0;
    }
}
