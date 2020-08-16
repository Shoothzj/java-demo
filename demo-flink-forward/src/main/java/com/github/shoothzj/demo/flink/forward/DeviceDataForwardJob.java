package com.github.shoothzj.demo.flink.forward;

import com.github.shoothzj.demo.base.mariadb.TestConstant;
import com.github.shoothzj.demo.base.test.module.TestDeviceDto;
import com.github.shoothzj.demo.base.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author hezhangjian
 */
@Slf4j
public class DeviceDataForwardJob {

    public static final String DST_TYPE = "dst.type";

    public static final String MYSQL_ADDR = "mysql.addr";

    /**
     * main() defines and executes the DataStream program.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        LogUtil.configureLog();
        ParameterTool parameterTool = ParameterTool.fromArgs(args);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<TestDeviceDto> streamSource = env.addSource(new DeviceDtoSource());
        if (!parameterTool.has(DST_TYPE)) {
            streamSource.print();
            return;
        }
        String dstType = parameterTool.get(DST_TYPE);
        switch (dstType) {
            case "Mysql":
                streamSource.addSink(new SinkToMySQL(parameterTool.get(MYSQL_ADDR)));
                break;
            default:
                break;
        }
        env.execute("test go");
    }

}
