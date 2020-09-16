package com.github.shoothzj.demo.jpath;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author hezhangjian
 */
@Slf4j
public class JsonPathTest {

    public static void main(String[] args) {
        DocumentContext jsonContext = JsonPath.parse(Constant.str);
        String jsonpathCreatorName = jsonContext.read("$.name");
        System.out.println(jsonpathCreatorName);
    }


    @Test
    public void test1() {
        DocumentContext jsonContext = JsonPath.parse(Constant.str);
        Object read = jsonContext.read("$.heroes.name");
        System.out.println(read);
    }

    @Test
    public void test2() {
        DocumentContext jsonContext = JsonPath.parse(Constant.str);
        Object read = jsonContext.read("$.heroes[0].name");
        System.out.println(read);
    }

}
