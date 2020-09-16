package com.github.shoothzj.demo.jpath;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author hezhangjian
 */
@Slf4j
public class JPathArrayExample {

    String msg = "{ \n" +
            "    \"e\": [\n" +
            "        { \"n\": \"temperature\", \"u\": \"Cel\", \"t\": 1234, \"v\": 22.5 },\n" +
            "        { \"n\": \"light\", \"u\": \"lm\", \"t\": 1235, \"v\": 135 },\n" +
            "        { \"n\": \"acidity\", \"u\": \"pH\", \"t\": 1235, \"v\": 7 }\n" +
            "    ]\n" +
            "}";

    @Test
    public void test() {
        DocumentContext documentContext = JsonPath.parse(msg);
        Object read = documentContext.read("$..n");
        System.out.println(read);
    }

    @Test
    public void testArray() {
        DocumentContext documentContext = JsonPath.parse(msg);
        Object read = documentContext.read("$.e");
        System.out.println(read);
    }

    @Test
    public void testSpecifyArray() {
        DocumentContext documentContext = JsonPath.parse(msg);
        Object read = documentContext.read("$.e[?(@.n=='temperature')].t");
        System.out.println(read.getClass());
        System.out.println(read);
    }

}
