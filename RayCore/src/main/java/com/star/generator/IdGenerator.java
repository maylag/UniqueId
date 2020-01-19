package com.star.generator;

import com.star.config.Injectors;
import com.star.constant.MetaConstants;
import com.star.meta.MachineIdFactory;
import com.star.meta.TimeAndSequences;
import com.star.meta.TimeStampAndSequence;

import javax.inject.Inject;

import static com.star.constant.MetaConstants.MACHINE_ID_SHIFT;
import static com.star.constant.MetaConstants.TIMESTAMP_LEFT_SHIFT;

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
    private TimeAndSequences timeAndSequences;

    private IdGenerator() {
        Injectors.inject(this);
    }

    public static IdGenerator getInstance() {
        return idGenerator;
    }

    public long getId() {

        // 获取机器标识
        long machineId = machineIdFactory.getMachineId();

        // 计算时间戳和序列
        TimeStampAndSequence timeStampAndSequence = timeAndSequences.calculate();

//        System.out.println("time is " + timeStampAndSequence.getTimestamp() + " machineId is " + machineId + " index is " + index + " sequence is " + timeStampAndSequence.getSequence());

        // 根据元数据生成ID
        return ((timeStampAndSequence.getTimestamp() - MetaConstants.ORIGIN_TIME_STAMP) << TIMESTAMP_LEFT_SHIFT)
                | (machineId << MACHINE_ID_SHIFT)
                | timeStampAndSequence.getSequence();
    }

}
