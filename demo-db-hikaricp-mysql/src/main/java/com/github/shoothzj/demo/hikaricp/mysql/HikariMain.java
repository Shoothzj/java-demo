package com.github.shoothzj.demo.hikaricp.mysql;

import com.github.shoothzj.demo.base.test.module.TestDeviceDto;
import com.github.shoothzj.demo.base.test.util.TestDataUtil;
import com.github.shoothzj.javatool.service.JacksonService;
import com.github.shoothzj.javatool.util.LogUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * @author hezhangjian
 */
@Slf4j
public class HikariMain {

    private static final String MYSQL_ADDR = "MYSQL_ADDR";

    private static final String HIKARI_MAX_SIZE = "HIKARI_MAX_SIZE";

    public static void main(String[] args) throws SQLException {
        LogUtil.configureLog();
        log.info("now is [{}]", LocalDateTime.now());
        String getenv = System.getenv(MYSQL_ADDR);
        int count = Integer.parseInt(System.getenv("COUNT"));
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + getenv + ":3306/ttbb?allowPublicKeyRetrieval=true");
        config.setUsername("hzj");
        config.setPassword("Mysql@123");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        HikariDataSource ds = new HikariDataSource(config);
        // create table
        PreparedStatement preparedStatement = ds.getConnection().prepareStatement("CREATE TABLE TEST_DEVICE(" +
                "DEVICE_NAME VARCHAR(100)," +
                "DEVICE_ID VARCHAR(100)," +
                "CREATE_TIME DATETIME," +
                "DEVICE_GROUP TEXT," +
                "TAGS TEXT" +
                ")");
        try {
            preparedStatement.execute();
        } catch (Exception e) {
            log.error("exception is ", e);
        }
        while (count > 0) {
            executeSql(ds.getConnection(), "INSERT INTO TEST_DEVICE (DEVICE_NAME, DEVICE_ID, CREATE_TIME, DEVICE_GROUP, TAGS) VALUES (?, ?, ?, ?, ?)", TestDataUtil.generateTestDeviceDto());
            count--;
        }
        log.info("now is [{}]", LocalDateTime.now());
    }

    public static void executeSql(Connection connection, String sql, TestDeviceDto testDeviceDto) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, testDeviceDto.getDeviceName());
            preparedStatement.setString(2, testDeviceDto.getDeviceId());
            preparedStatement.setString(3, testDeviceDto.getCreateTime().toString());
            preparedStatement.setString(4, JacksonService.toJson(testDeviceDto.getDeviceGroup()));
            preparedStatement.setString(5, JacksonService.toJson(testDeviceDto.getTags()));
            preparedStatement.execute();
        } catch (Exception e) {
            log.error("insert exception is [{}]", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                log.error("throwable is ", throwables);
            }
        }
    }

}
