package com.github.shoothzj.demo.springboot.mysql.controller;

import com.github.shoothzj.demo.springboot.mysql.domain.User;
import com.github.shoothzj.demo.springboot.mysql.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class MysqlTestController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/test")
    public String test() {
        return "test";
    }

    @RequestMapping(path = "/get")
    public ResponseEntity<String> get() {
        User user = new User();
        user.setUsername("");
        User save = userRepository.save(user);
        userRepository.save(save);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
