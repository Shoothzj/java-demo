package com.github.shoothzj.demo.db.jdbc.mariadb;

import com.github.shoothzj.demo.base.mariadb.module.FieldDescribe;
import com.github.shoothzj.javatool.util.LogUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author hezhangjian
 */
@Slf4j
public class MariadbDescribeTable {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        LogUtil.configureLog();
        Class.forName("org.mariadb.jdbc.Driver");
        // Now try to connect
        Properties p = new Properties();
        p.put("user", "hzj");
        p.put("password", "Mysql@123");
        try (Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ttbb", p)) {
            {
                PreparedStatement preparedStatement = c.prepareStatement("CREATE TABLE XX(\n" +
                        "  id VARCHAR(50) PRIMARY KEY,\n" +
                        "  male BOOLEAN,\n" +
                        "  weight DOUBLE,\n" +
                        "  age INT,\n" +
                        "  age2 INTEGER\n" +
                        ")");
                preparedStatement.execute();
            }
            {
                PreparedStatement statement = c.prepareStatement("SELECT * FROM XX LIMIT 1");
                ResultSetMetaData metaData = statement.getMetaData();
                int size = metaData.getColumnCount();
                for (int i = 1; i <= size; i++) {
                    FieldDescribe fieldDescribe = new FieldDescribe();
                    fieldDescribe.setColumnType(metaData.getColumnType(i));
                    fieldDescribe.setColumnTypeName(metaData.getColumnTypeName(i));
                    fieldDescribe.setColumnDisplaySize(metaData.getColumnDisplaySize(i));
                    fieldDescribe.setColumnLabel(metaData.getColumnLabel(i));
                    log.info("[{}]", fieldDescribe);
                }
            }
            {
                PreparedStatement preparedStatement = c.prepareStatement("DROP TABLE XX");
                preparedStatement.execute();
            }
        }
    }

}
