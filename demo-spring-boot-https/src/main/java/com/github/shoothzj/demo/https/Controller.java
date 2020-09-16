package com.github.shoothzj.demo.https;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hezhangjian
 */
@Slf4j
@RestController
public class Controller {


    @GetMapping(path = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String hello() {
        log.info("hello");
        return "hello";
    }

    @PostMapping(path = "/receive", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Void> receive(@RequestBody String s) {
        log.info("body is [{}]", s);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}