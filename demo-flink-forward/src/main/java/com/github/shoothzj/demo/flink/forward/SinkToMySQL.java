package com.github.shoothzj.demo.flink.forward;

import com.github.shoothzj.demo.base.mariadb.MariaUtil;
import com.github.shoothzj.demo.base.mariadb.TestConstant;
import com.github.shoothzj.demo.base.test.module.TestDeviceDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.IterationRuntimeContext;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * 数据批量sink到MySQL
 * @author hezhangjian
 */
@Slf4j
public class SinkToMySQL extends RichSinkFunction<TestDeviceDto> {

    private final String mysqlAddr;

    private Connection connection;

    public SinkToMySQL(String mysqlAddr) {
        this.mysqlAddr = mysqlAddr;
    }

    @Override
    public void setRuntimeContext(RuntimeContext t) {
        super.setRuntimeContext(t);
    }

    @Override
    public RuntimeContext getRuntimeContext() {
        return super.getRuntimeContext();
    }

    @Override
    public IterationRuntimeContext getIterationRuntimeContext() {
        return super.getIterationRuntimeContext();
    }

    /**
     * open () 方法中建立连接，这样不用每次invoke的时候都要建立连接，
     * @param parameters
     * @throws Exception
     */
    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        Class.forName(TestConstant.DRIVER_NAME);
        this.connection = DriverManager.getConnection(MariaUtil.getConnStr(mysqlAddr), MariaUtil.getProperties());
        PreparedStatement stat = connection.prepareStatement("CREATE TABLE Device (\n" +
                "  deviceName VARCHAR(200) PRIMARY KEY,\n" +
                "  deviceId VARCHAR(200)\n" +
                ");");
        stat.execute();
    }

    /**
     * 每条数据的插入都要调用一次invoke方法
     * @param value
     * @param context
     * @throws Exception
     */
    @Override
    public void invoke(TestDeviceDto value, Context context) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement("insert INTO Device (deviceName, deviceId) values (?, ?)");
        preparedStatement.setString(1, value.getDeviceName());
        preparedStatement.setString(2, value.getDeviceId());
        preparedStatement.execute();
    }

    @Override
    public void close() throws Exception {
        super.close();
    }
}
