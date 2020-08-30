package com.github.shoothzj.demo.influxdb.pf;

import com.github.shoothzj.demo.base.test.module.TestElevatorSensorDto;
import com.github.shoothzj.demo.base.test.util.TestDataUtil;
import com.github.shoothzj.javatool.util.CommonUtil;
import com.github.shoothzj.javatool.util.EnvUtil;
import com.github.shoothzj.javatool.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

import java.util.concurrent.TimeUnit;

/**
 * @author hezhangjian
 */
@Slf4j
public class PfInfluxdbProducer {

    public static void main(String[] args) {
        LogUtil.configureLog();
        String influxAddr = EnvUtil.getStringVar("influx.addr", "INFLUX_ADDR", "localhost");
        int testCount = EnvUtil.getIntVar("influx.test.count", "INFLUX_TEST_COUNT", 10_000);
        // Create an object to handle the communication with InfluxDB.
        // (best practice tip: reuse the 'influxDB' instance when possible)
        String serverURL = String.format("http://%s:8086", influxAddr);

        String username = "root";
        String password = "root";

        final InfluxDB influxdb = InfluxDBFactory.connect(serverURL, username, password);

        // Create a database...
        String databaseName = "TEST_PF";
        influxdb.query(new Query("CREATE DATABASE " + databaseName));
        influxdb.setDatabase(databaseName);

        String retentionPolicyName = "one_day_only";
        influxdb.query(new Query("CREATE RETENTION POLICY " + retentionPolicyName
                + " ON " + databaseName + " DURATION 1d REPLICATION 1 DEFAULT"));
        influxdb.setRetentionPolicy(retentionPolicyName);

        influxdb.enableBatch(BatchOptions.DEFAULTS);

        long startTime = System.currentTimeMillis();
        log.info("start time is [{}]", startTime);
        // 写入测试数据
        for (int i = 0; i < testCount; i++) {
            TestElevatorSensorDto testElevatorSensorDto = TestDataUtil.generateTestElevatorSensorDto();
            influxdb.write(Point.measurement("test_db3")
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .tag("DeviceName", testElevatorSensorDto.getDeviceName())
                    .addField("high", testElevatorSensorDto.getRandomFloat())
                    .addField("water_level", 8.12d)
                    .build());
        }
        log.info("cost time is [{}]", System.currentTimeMillis() - startTime);

        influxdb.close();

    }

}
