package com.github.shoothzj.demo.db.jdbc.mariadb;

import com.github.shoothzj.demo.db.jdbc.mariadb.util.MariaUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author akka
 */
public class MariaDBBegin {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName(TestConstant.DRIVER_NAME);
        // Now try to connect
        try (Connection c = DriverManager.getConnection(MariaUtil.getConnStr(), MariaUtil.getProperties());) {
            System.out.println("It works !");
        }
    }

}