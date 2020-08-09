package com.github.shoothzj.demo.db.cassandra.util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hezhangjian
 */
@Slf4j
public class CassandraUtil {

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
