package com.github.shoothzj.demo.sb.mongo.repo;

import com.github.shoothzj.demo.sb.mongo.dao.PersonDao;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author hezhangjian
 */
public interface PersonRepository extends MongoRepository<PersonDao, String> {
}
