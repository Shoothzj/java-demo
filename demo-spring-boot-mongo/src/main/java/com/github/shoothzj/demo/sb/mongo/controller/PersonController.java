package com.github.shoothzj.demo.sb.mongo.controller;

import com.github.shoothzj.demo.sb.mongo.dao.PersonDao;
import com.github.shoothzj.demo.sb.mongo.repo.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/persons")
    public void get() {
        List<PersonDao> personDaoList = personRepository.findAll();
        log.info(personDaoList.toString());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/person")
    public void post(@RequestBody String user) {
        log.info("");
        final PersonDao personDao = new PersonDao();
        personDao.setId("personId");
        personDao.setBirthTime(LocalDateTime.now());
        personDao.setPersonName("personName");
        personRepository.save(personDao);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/person/single")
    public void getSingle() {
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/person")
    public void delete() {
    }

}