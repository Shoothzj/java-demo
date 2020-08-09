package com.github.shoothzj.demo.db.cassandra.singlefield;

import com.datastax.oss.driver.api.core.CqlSession;
import com.github.shoothzj.demo.db.cassandra.util.CassandraTestUtil;
import com.github.shoothzj.demo.db.singlefield.TestDouble;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Test;

/**
 * @author hezhangjian
 */
@Slf4j
public class CassandraTestInt {

    @Test
    public void createTable() {
        Configurator.setRootLevel(Level.INFO);
        String ddl = CassandraTestUtil.concatCreateTable(TestDouble.class, "INT");
        try (CqlSession session = CqlSession.builder().withKeyspace("sh").build()) {
            session.execute(ddl);
        }
    }

}
