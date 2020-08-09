package com.github.shoothzj.demo.db.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author hezhangjian
 */
@Slf4j
public class CassandraSession {

    public static void main(String[] args) throws Exception {
        try (CqlSession session = CqlSession.builder().build()) {
            ResultSet rs = session.execute("select release_version from system.local");
            Row row = rs.one();
            System.out.println(row.getString("release_version"));
        }
    }

}
