package com.github.shoothzj.demo.db.jdbc.mariadb.util;

import com.github.shoothzj.demo.db.jdbc.mariadb.TestConstant;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author hezhangjian
 */
@Slf4j
public class MariaUtil {

    public static String getConnStr() {
        return "jdbc:mariadb://localhost:3306/" + TestConstant.TABLE_NAME;
    }

    public static Properties getProperties() {
        // Properties for user and password.
        Properties p = new Properties();
        p.put("user", TestConstant.USER_NAME);
        p.put("password", TestConstant.PASSWORD);
        return p;
    }

    public static String concatCreateTable(String tableName, String... columnDesc) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append(tableName);
        sb.append("(");
        List<String> auxList = new ArrayList<>();
        for (int i = 0; i < columnDesc.length; i+=2) {
            auxList.add(columnDesc[i] + " " + columnDesc[i+1]);
        }
        String collect = auxList.stream().collect(Collectors.joining(","));
        sb.append(collect);
        sb.append(")");
        String s = sb.toString();
        log.info("ddl is [{}] ", s);
        return s;
    }

}
