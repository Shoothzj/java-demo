package com.github.shoothzj.demo.db.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Test;

/**
 * @author hezhangjian
 */
@Slf4j
public class CassandraTestKeySpace {

    @Test
    public void createKeySpace() {
        Configurator.setRootLevel(Level.INFO);
        try (CqlSession session = CqlSession.builder().build()) {
            ResultSet execute = session.execute("CREATE KEYSPACE sh WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3}");
            log.info("result set is [{}] ", execute);
        }
    }

}
