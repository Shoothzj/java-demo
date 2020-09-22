package com.github.shoothzj.demo.basic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.Instant;

/**
 * @author hezhangjian
 */
@Slf4j
public class JacksonTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void test1() throws JsonProcessingException {
        final TestJacksonModule testJacksonModule = new TestJacksonModule();
        testJacksonModule.setInstant(Instant.now());
        final String json = objectMapper.writeValueAsString(testJacksonModule);
        log.info(json);
        final TestJacksonModule jacksonModule = objectMapper.readValue(json, TestJacksonModule.class);
        log.info("[{}]", jacksonModule);
    }

    @Test
    public void test2() throws JsonProcessingException {
        final TestJacksonModule1 testJacksonModule1 = new TestJacksonModule1();
        testJacksonModule1.setAux("aux");
        testJacksonModule1.setName("name");
        final String json = objectMapper.writeValueAsString(testJacksonModule1);
        log.info(json);
        final TestJacksonModule2 jacksonModule = objectMapper.readValue(json, TestJacksonModule2.class);
        log.info("[{}]", jacksonModule);
    }

}
