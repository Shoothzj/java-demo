package com.github.shoothzj.demo.db.jdbc.mariadb.singlefield.util;

import com.github.shoothzj.demo.db.annotation.Table;
import com.github.shoothzj.demo.db.jdbc.mariadb.util.MariaUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author hezhangjian
 */
@Slf4j
public class MariadbTestUtil {

    public static <T> void dropTable(Class<T> type) throws Exception {
        String createTable = MariadbTestUtil.concatDropTable(type);
        try (Connection c = DriverManager.getConnection(MariaUtil.getConnStr(), MariaUtil.getProperties());) {
            Statement statement = c.createStatement();
            int update = statement.executeUpdate(createTable);
            log.info("update is [{}]", update);
        }
    }

    public static <T> String concatCreateTable(Class<T> type, String columnDesc) {
        Table typeAnnotation = type.getAnnotation(Table.class);
        return MariaUtil.concatCreateTable(typeAnnotation.name(), "id"
                , "VARCHAR(200) PRIMARY KEY", "field", columnDesc);
    }

    public static <T> String concatInsert(Class<T> type) {
        Table typeAnnotation = type.getAnnotation(Table.class);
        return String.format("insert into %s (id, field) values (?, ?)", typeAnnotation.name());
    }

    public static <T> String concatQuery(Class<T> type) {
        Table typeAnnotation = type.getAnnotation(Table.class);
        return String.format("SELECT * FROM %s", typeAnnotation.name());
    }

    public static <T> String concatDropTable(Class<T> type) {
        Table typeAnnotation = type.getAnnotation(Table.class);
        return "DROP TABLE " + typeAnnotation.name();
    }

}
