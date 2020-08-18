package com.github.shoothzj.demo.flink.forward;

import com.github.shoothzj.demo.base.test.module.DeviceFlinkDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

/**
 * @author hezhangjian
 */
@Slf4j
public class DeviceKeyedProcessFunction extends KeyedProcessFunction<String, DeviceFlinkDto, String> {

    @Override
    public void processElement(DeviceFlinkDto value, Context ctx, Collector<String> out) throws Exception {

    }



}
