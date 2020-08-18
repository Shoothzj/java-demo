package com.github.shoothzj.demo.flink.forward;

import com.github.shoothzj.demo.base.test.module.DeviceFlinkDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

import javax.annotation.Nullable;

/**
 * @author hezhangjian
 */
@Slf4j
public class PeriodicAssigner implements AssignerWithPeriodicWatermarks<DeviceFlinkDto> {

    private final long bound = 60 * 1000;

    private long maxTs = Long.MIN_VALUE;

    @Nullable
    @Override
    public Watermark getCurrentWatermark() {
        return new Watermark(maxTs - bound);
    }

    @Override
    public long extractTimestamp(DeviceFlinkDto element, long recordTimestamp) {
        maxTs = Math.max(element.getTimestamp(), maxTs);
        return element.getTimestamp();
    }

}
