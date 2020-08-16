package com.github.shoothzj.demo.flink.forward;

import com.github.shoothzj.demo.base.test.module.TestDeviceDto;
import com.github.shoothzj.demo.base.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author hezhangjian
 */
@Slf4j
public class DeviceDataForwardJob {

    public static final String DST_TYPE = "Mysql";

    /**
     * main() defines and executes the DataStream program.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        LogUtil.configureLog();
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        DataStreamSource<TestDeviceDto> streamSource = env.addSource(new DeviceDtoSource());
        streamSource.print();
        env.execute("test go");
    }

}
