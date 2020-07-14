package com.github.shoothzj.demo.springboot.mysql;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author hezhangjian
 */
@Slf4j
@EnableJpaAuditing
@EnableJpaRepositories({"com.github.shoothzj.demo.springboot.mysql.repository"})
@SpringBootApplication(scanBasePackages = {"com.github.shoothzj.demo.springboot.mysql"})
@EntityScan(basePackages = {"com.github.shoothzj.demo.springboot.mysql.domain"})
public class MysqlDemo {

    public static void main(String[] args) {
        SpringApplication.run(MysqlDemo.class);
    }

}
