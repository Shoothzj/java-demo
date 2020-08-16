package com.github.shoothzj.demo.flink.forward;

import com.github.shoothzj.demo.base.test.module.TestDeviceDto;
import com.github.shoothzj.demo.base.test.util.TestDataUtil;
import com.github.shoothzj.demo.base.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

import java.util.concurrent.TimeUnit;

/**
 * @author hezhangjian
 */
@Slf4j
public class DeviceDtoSource extends RichParallelSourceFunction<TestDeviceDto> {

    private volatile boolean running = true;

    @Override
    public void run(SourceContext<TestDeviceDto> ctx) throws Exception {
        while (running) {
            ctx.collect(TestDataUtil.generateTestDeviceDto());
            CommonUtil.sleep(TimeUnit.MILLISECONDS, 100);
        }
    }

    @Override
    public void cancel() {
        running = false;
    }
}
