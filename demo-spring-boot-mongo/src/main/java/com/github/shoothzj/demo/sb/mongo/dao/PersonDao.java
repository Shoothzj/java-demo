package com.github.shoothzj.demo.sb.mongo.dao;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * @author hezhangjian
 */
@Data
@Document(collection = "T_Person")
public class PersonDao {

    @Id
    private String id;

    @Field("personName")
    private String personName;

    @Field("birthTime")
    private LocalDateTime birthTime;

}
