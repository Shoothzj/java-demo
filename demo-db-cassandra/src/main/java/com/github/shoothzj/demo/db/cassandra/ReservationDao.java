package com.github.shoothzj.demo.db.cassandra;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.*;
import com.github.shoothzj.demo.db.cassandra.module.ReservationsByConfirmation;

@Dao
public interface ReservationDao {

    @Select
    ReservationsByConfirmation findByConfirmationNumber(String confirmNumber);

    @Query("SELECT * FROM ${tableId}")
    PagingIterable<ReservationsByConfirmation> findAll();

    @Insert
    void save(ReservationsByConfirmation reservationsByConfirmation);

    @Delete
    void delete(ReservationsByConfirmation reservationsByConfirmation);

}