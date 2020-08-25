package com.github.shoothzj.demo.db.jdbc.mariadb.util;

import com.github.shoothzj.demo.base.mariadb.MariaUtil;
import com.github.shoothzj.demo.db.annotation.Table;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author hezhangjian
 */
@Slf4j
public class MariaSingleFieldUtil {

    public static <T> void createTable(Class<T> type, String columnDesc) throws Exception {
        String createTable = MariaSingleFieldUtil.concatCreateTable(type, columnDesc);
        try (Connection c = DriverManager.getConnection(MariaUtil.getConnStr(), MariaUtil.getProperties());) {
            Statement statement = c.createStatement();
            statement.executeUpdate(createTable);
        }
    }

    public static <T> void dropTable(Class<T> type) throws Exception {
        String createTable = MariaSingleFieldUtil.concatDropTable(type);
        try (Connection c = DriverManager.getConnection(MariaUtil.getConnStr(), MariaUtil.getProperties());) {
            Statement statement = c.createStatement();
            statement.executeUpdate(createTable);
        }
    }

    public static <T> String concatCreateTable(Class<T> type, String columnDesc) {
        Table typeAnnotation = type.getAnnotation(Table.class);
        return MariaUtil.concatCreateTable(typeAnnotation.name(), "id"
                , "VARCHAR(100) PRIMARY KEY", "field", columnDesc);
    }

    public static <T> String concatInsert(Class<T> type) {
        Table typeAnnotation = type.getAnnotation(Table.class);
        return String.format("insert INTO %s (id, field) values (?, ?)", typeAnnotation.name());
    }

    public static <T> String concatQuery(Class<T> type) {
        Table typeAnnotation = type.getAnnotation(Table.class);
        return String.format("SELECT * FROM %s", typeAnnotation.name());
    }

    public static <T> String concatDropTable(Class<T> type) {
        Table typeAnnotation = type.getAnnotation(Table.class);
        return "DROP TABLE " + typeAnnotation.name();
    }

    public static <T> String getTableName(Class<T> type) {
        Table typeAnnotation = type.getAnnotation(Table.class);
        return typeAnnotation.name();
    }

}
