package com.github.shoothzj.demo.basic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.shoothzj.javatool.service.JacksonService;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Map;

/**
 * @author hezhangjian
 */
@Slf4j
public class JacksonOrderTest {

    public static void main(String[] args) {
        String def = "{'services':1,'taskId':1, 'az':1}";
        String def2 = "{\"services\":1,\"taskId\":2, \"az\":4}";
        final ObjectNode objectNode = JacksonService.toObject(def2, ObjectNode.class);
        final Iterator<Map.Entry<String, JsonNode>> iterator = objectNode.fields();
        while (iterator.hasNext()) {
            final Map.Entry<String, JsonNode> jsonNodeEntry = iterator.next();
            log.info("json node is [{}]", jsonNodeEntry);
        }
    }

}
