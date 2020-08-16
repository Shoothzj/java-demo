package com.github.shoothzj.demo.db.jdbc.mariadb.singlefield;

import com.github.shoothzj.demo.base.mariadb.TestConstant;
import com.github.shoothzj.demo.base.mariadb.module.FieldDescribe;
import com.github.shoothzj.demo.db.jdbc.mariadb.util.MariaSingleFieldUtil;
import com.github.shoothzj.demo.base.mariadb.MariaUtil;
import com.github.shoothzj.demo.db.singlefield.TestBoolean;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author hezhangjian
 */
@Slf4j
public class MariadbJdbcTestBoolean {

    /**
     * init jdbc driver and log4j
     */
    @Before
    public void init() throws ClassNotFoundException {
        Configurator.setRootLevel(Level.INFO);
        Class.forName(TestConstant.DRIVER_NAME);
    }

    @Test
    public void integrate() throws Exception {
        log.info("start");
        createTable();
        insertData();
        queryData();
        dropTable();
    }

    @Test
    @Ignore
    public void createTable() throws Exception {
        MariaSingleFieldUtil.createTable(TestBoolean.class, "BOOLEAN");
    }

    @Test
    @Ignore
    public void describeTable() throws Exception {
        Map<String, FieldDescribe> map = new HashMap<>();
        String querySql = MariaSingleFieldUtil.concatQuery(TestBoolean.class);
        try (Connection c = DriverManager.getConnection(MariaUtil.getConnStr(), MariaUtil.getProperties());) {
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(querySql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int size = metaData.getColumnCount();
            for (int i = 1; i <= size; i++) {
                FieldDescribe fieldDescribe = new FieldDescribe();
                fieldDescribe.setColumnType(metaData.getColumnType(i));
                fieldDescribe.setColumnTypeName(metaData.getColumnTypeName(i));
                fieldDescribe.setColumnDisplaySize(metaData.getColumnDisplaySize(i));
                fieldDescribe.setColumnLabel(metaData.getColumnLabel(i));
                map.put(metaData.getColumnName(i), fieldDescribe);
            }
        }
        log.info("map is [{}]", map);
    }

    @Test
    @Ignore
    public void insertData() throws Exception {
        String insertSql = MariaSingleFieldUtil.concatInsert(TestBoolean.class);
        try (Connection c = DriverManager.getConnection(MariaUtil.getConnStr(), MariaUtil.getProperties());) {
            PreparedStatement statement = c.prepareStatement(insertSql);
            statement.setString(1, UUID.randomUUID().toString());
            statement.setBoolean(2, true);
            boolean execute = statement.execute();
            log.info("execute result is [{}]", execute);
        }
    }

    @Test
    @Ignore
    public void queryData() throws Exception {
        String querySql = MariaSingleFieldUtil.concatQuery(TestBoolean.class);
        try (Connection c = DriverManager.getConnection(MariaUtil.getConnStr(), MariaUtil.getProperties());) {
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(querySql);
            Assert.assertTrue(resultSet.next());
            log.info("id is [{}], field is [{}]", resultSet.getString("id"), resultSet.getBoolean("field"));
            Assert.assertFalse(resultSet.next());
        }
    }

    @Test
    @Ignore
    public void dropTable() throws Exception {
        MariaSingleFieldUtil.dropTable(TestBoolean.class);
    }

}
