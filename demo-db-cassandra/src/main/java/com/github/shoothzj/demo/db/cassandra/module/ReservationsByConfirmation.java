package com.github.shoothzj.demo.db.cassandra.module;

import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.NamingStrategy;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

import static com.datastax.oss.driver.api.mapper.entity.naming.NamingConvention.SNAKE_CASE_INSENSITIVE;

/**
 * @author hezhangjian
 */
@Data
@Entity
@NamingStrategy(convention = SNAKE_CASE_INSENSITIVE)
public class ReservationsByConfirmation {

    @PartitionKey
    private String confirmNumber;

    private String hotelId;
    private LocalDate startDate;
    private LocalDate endDate;
    private short roomNumber;
    private UUID guestId;

    public ReservationsByConfirmation() {
    }
}