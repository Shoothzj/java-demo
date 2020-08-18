package com.github.shoothzj.demo.hikaricp;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author hezhangjian
 */
@Slf4j
public class HikariTool {

    static final HikariDataSource ds;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/ttbb?allowPublicKeyRetrieval=true");
        config.setUsername("hzj");
        config.setPassword("Mysql@123");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        ds = new HikariDataSource(config);
    }

    public static void executeSql(String sql) throws SQLException {
        PreparedStatement preparedStatement = ds.getConnection().prepareStatement(sql);
        preparedStatement.execute();
    }

}
