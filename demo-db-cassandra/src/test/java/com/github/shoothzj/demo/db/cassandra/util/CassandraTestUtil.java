package com.github.shoothzj.demo.db.cassandra.util;

import com.github.shoothzj.demo.db.annotation.Table;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hezhangjian
 */
@Slf4j
public class CassandraTestUtil {

    public static <T> String concatCreateTable(Class<T> type, String columnDesc) {
        Table typeAnnotation = type.getAnnotation(Table.class);
        return CassandraUtil.concatCreateTable(typeAnnotation.name(), "id"
                , "TEXT PRIMARY KEY", "field", columnDesc);
    }

    public static <T> String concatDropTable(Class<T> type) {
        Table typeAnnotation = type.getAnnotation(Table.class);
        return "DROP TABLE " + typeAnnotation.name();
    }

}
