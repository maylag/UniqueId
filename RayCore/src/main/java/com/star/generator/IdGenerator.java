package com.star.generator;

import com.star.config.Injectors;
import com.star.meta.MachineIdFactory;
import com.star.meta.Partitions;
import com.star.meta.SequenceFactory;
import com.star.meta.InitTimeStampFactory;

import javax.inject.Inject;

import static com.star.constant.MetaConstants.*;

/**
 * @program: Ray
 * @description:
 * @author: liu na
 * @create: 2019-07-21 18:56
 */
public final class IdGenerator {

    private static final String DEFAULT = "default";

    private static IdGenerator idGenerator = new IdGenerator();

    @Inject
    private MachineIdFactory machineIdFactory;

    @Inject
    private InitTimeStampFactory timeStampFactory;

    @Inject
    private SequenceFactory sequenceFactory;

    private IdGenerator(){
        Injectors.inject(this);
    }

    public static IdGenerator getInstance() {
        return idGenerator;
    }

    public long getId(){
        return getId(DEFAULT);
    }

    public long getId(String domain) {

        // 获取机器标识
        long machineId = machineIdFactory.getMachineId();

        // 获取分区
        long index = Partitions.getIndex(domain);

        // 获取时间戳, max(current, last)
        long timeStamp = timeStampFactory.getTimeStamp(index);

        // 获取序列
        long sequence = sequenceFactory.getSequence(index, timeStamp);

        // 根据元数据生成ID
        return (timeStamp << TIMESTAMP_LEFT_SHIFT) | (machineId << MACHINE_ID_SHIFT) | (index << DOMAIN_ID_SHIFT) | sequence;
    }

}
