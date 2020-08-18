package com.github.shoothzj.demo.hikaricp;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.PrintWriter;
import java.util.Properties;

/**
 * Four ways to Init Hikari Config.
 * @author hezhangjian
 */
@Slf4j
public class HikariConfigInitTest {

    @Test
    public void way1() {
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(8);
        config.setPoolName("Pool1");
        HikariDataSource ds1 = new HikariDataSource(config);
    }

    @Test
    public void way2() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/simpsons");
        ds.setUsername("bart");
        ds.setPassword("51mp50n");
    }

    @Test
    public void way3() {
        HikariConfig config = new HikariConfig("/some/path/hikari.properties");
        HikariDataSource hikariDataSource = new HikariDataSource(config);
    }

    @Test
    public void way4() {
        Properties props = new Properties();
        props.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
        props.setProperty("dataSource.user", "test");
        props.setProperty("dataSource.password", "test");
        props.setProperty("dataSource.databaseName", "mydb");
        props.put("dataSource.logWriter", new PrintWriter(System.out));

        HikariConfig config = new HikariConfig(props);
        HikariDataSource ds = new HikariDataSource(config);
    }

}
