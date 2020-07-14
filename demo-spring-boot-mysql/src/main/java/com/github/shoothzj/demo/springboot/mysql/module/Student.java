package com.github.shoothzj.demo.springboot.mysql.module;

import lombok.Data;

import java.util.Date;

/**
 * @author hezhangjian
 */
@Data
public class Student {

    /**
     * 学生id
     */
    private int id;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生电子邮箱
     */
    private String email;

    private Date createDate;

    public Student() {
    }

    public Student(int id, String name, String email, Date createDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createDate = createDate;
    }
}
