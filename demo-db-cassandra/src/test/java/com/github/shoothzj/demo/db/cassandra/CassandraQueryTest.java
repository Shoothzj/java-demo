package com.github.shoothzj.demo.db.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Test;

/**
 * @author hezhangjian
 */
@Slf4j
public class CassandraQueryTest {

    @Test
    public void query() {
        Configurator.setRootLevel(Level.INFO);
        CqlSessionBuilder cqlSessionBuilder = CqlSession.builder();
        cqlSessionBuilder.withKeyspace("sh");
        cqlSessionBuilder.withLocalDatacenter("dataCenter");
        try (CqlSession session = cqlSessionBuilder.build()) {
        }
    }

    @Test
    public void query_1() {
        Configurator.setRootLevel(Level.INFO);
        CqlSessionBuilder cqlSessionBuilder = CqlSession.builder();
        cqlSessionBuilder.withLocalDatacenter("dataCenter");
        try (CqlSession session = cqlSessionBuilder.build()) {
            session.execute(SimpleStatement.newInstance("SELECT * from reservation.reservations_by_confirmation"));
        }
    }

    @Test
    public void query_2() {
        Configurator.setRootLevel(Level.INFO);
        CqlSessionBuilder cqlSessionBuilder = CqlSession.builder();
        cqlSessionBuilder.withLocalDatacenter("dataCenter");
        try (CqlSession session = cqlSessionBuilder.build()) {
            SimpleStatementBuilder builder = SimpleStatement.builder("INSERT INTO reservations_by_confirmation (confirm_number," +
                    " hotel_id, start_date, end_date, room_number, guest_id) VALUES (?, ?, ?, ?, ?, ?)");
            builder.addPositionalValue("RS2G0Z")
                    .addPositionalValue("NY456")
                    .addPositionalValue("2020-06-08")
                    .addPositionalValue("2020-06-10")
                    .addPositionalValue(111)
                    .addPositionalValue("1b4d86f4-ccff-4256-a63d-45c905df2677");
            session.execute(builder.build());
        }
    }

    @Test
    public void prepareQuery() {
        Configurator.setRootLevel(Level.INFO);
        CqlSessionBuilder cqlSessionBuilder = CqlSession.builder();
        cqlSessionBuilder.withLocalDatacenter("dataCenter");

        try (CqlSession cqlSession = cqlSessionBuilder.build()) {
            PreparedStatement reservationInsertPrepared = cqlSession.prepare(
                    "INSERT INTO reservations_by_confirmation (confirm_number, hotel_id, start_date, end_date, room_number, guest_id) VALUES( ?, ?, ?, ?, ?, ?)");

            PreparedStatement reservationSelectPrepared = cqlSession.prepare(
                    "SELECT * FROM reservations_by_confirmation WHERE confirm_number=?");

            reservationSelectPrepared.bind("RS2G0Z");
        }

    }

}