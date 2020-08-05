package com.github.shoothzj.demo.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

/**
 * @author hezhangjian
 */
@Slf4j
public class CassandraKeySpaces {

    public static void main(String[] args) throws Exception {
        Configurator.setRootLevel(Level.INFO);
        try (CqlSession session = CqlSession.builder().build()) {
            ResultSet rs = session.execute("DESCRIBE KEYSPACES");
            for (Row r : rs) {
                log.info("key space name is [{}], type is [{}], name is [{}]", r.getString("keyspace_name"),
                        r.getString("type"), r.getString("name"));
            }
        }
    }

}
