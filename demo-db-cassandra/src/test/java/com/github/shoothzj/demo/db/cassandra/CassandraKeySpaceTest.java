package com.github.shoothzj.demo.db.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Test;

/**
 * @author hezhangjian
 */
@Slf4j
public class CassandraKeySpaceTest {

    @Test
    public void createKeySpace() {
        Configurator.setRootLevel(Level.INFO);
        try (CqlSession session = CqlSession.builder().build()) {
            ResultSet execute = session.execute("CREATE KEYSPACE sh WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 1}");
            log.info("result set is [{}] ", execute);
        }
    }

    @Test
    public void describeKeySpace() {
        Configurator.setRootLevel(Level.INFO);
        try (CqlSession session = CqlSession.builder().build()) {
            ResultSet execute = session.execute("DESCRIBE KEYSPACE sh;");
            final Row row = execute.one();
            if (row == null) {
                return;
            }
            log.info("keyspace name is [{}]", row.getString("keyspace_name"));
            log.info("type is [{}]", row.getString("type"));
            log.info("name is [{}]", row.getString("name"));
            log.info("create stat is [{}]", row.getString("create_statement"));
        }
    }

}
