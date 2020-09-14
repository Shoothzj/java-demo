package com.github.shoothzj.demo.db.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.type.DataTypes;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Test;

import static com.datastax.oss.driver.api.querybuilder.SchemaBuilder.createTable;

/**
 * @author hezhangjian
 */
@Slf4j
public class CassandraTableTest {

    @Test
    public void createTableTest() {
        Configurator.setRootLevel(Level.INFO);
        CqlSessionBuilder cqlSessionBuilder = CqlSession.builder();
        cqlSessionBuilder.withKeyspace("sh");
        cqlSessionBuilder.withLocalDatacenter("dataCenter");
        try (CqlSession cqlSession = cqlSessionBuilder.build()) {
            cqlSession.execute(createTable("reservation", "reservations_by_confirmation")
                    .ifNotExists()
                    .withPartitionKey("confirm_number", DataTypes.TEXT)
                    .withColumn("hotel_id", DataTypes.TEXT)
                    .withColumn("start_date", DataTypes.DATE)
                    .withColumn("end_date", DataTypes.DATE)
                    .withColumn("room_number", DataTypes.SMALLINT)
                    .withColumn("guest_id", DataTypes.UUID)
                    .build());
        }
    }

}