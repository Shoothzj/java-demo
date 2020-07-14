package com.github.shoothzj.demo.springboot.mysql.repository;

import com.github.shoothzj.demo.springboot.mysql.module.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author hezhangjian
 */
@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Student> findAll() {
        List<Student> result = jdbcTemplate.query("SELECT id, name, email, created_date FROM Student",
                (rs, rowNum) -> new Student(rs.getInt("id"), rs.getString("name"),
                        rs.getString("email"), rs.getDate("created_date")));
        return result;
    }

    public void addStudent(String name, String email) {
        jdbcTemplate.update("INSERT INTO Student(name, email, created_date) VALUES (?,?,?)", name, email, new Date());
    }

}
