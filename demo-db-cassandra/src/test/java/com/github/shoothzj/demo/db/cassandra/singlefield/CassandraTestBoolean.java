package com.github.shoothzj.demo.db.cassandra.singlefield;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.github.shoothzj.demo.db.cassandra.util.CassandraUtil;
import com.github.shoothzj.demo.db.singlefield.TestBoolean;
import com.github.shoothzj.demo.db.annotation.Table;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Test;

/**
 * @author hezhangjian
 */
@Slf4j
public class CassandraTestBoolean {

    @Test
    public void createTable() {
        Configurator.setRootLevel(Level.INFO);
        Table annotation = TestBoolean.class.getAnnotation(Table.class);
        String createTable = CassandraUtil.concatCreateTable(annotation.name(), "id", "TEXT PRIMARY KEY", "field", "BOOLEAN");
        log.info("ddl is [{}]", createTable);
        try (CqlSession session = CqlSession.builder().withKeyspace("sh").build()) {
            ResultSet resultSet = session.execute(createTable);
            Row row = resultSet.one();
            log.info("result set is [{}] ", row);
        }
    }

    @Test
    public void insertData() {

    }

}
