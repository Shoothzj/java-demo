package com.github.shoothzj.demo.basic;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hezhangjian
 */
@Slf4j
public class ReflectDemo {

    public static void main(String[] args) throws Throwable {
        Class<?> name = Class.forName("com.github.shoothzj.demo.basic.ReflectDemo");
        Method doSth = name.getMethod("doSth", int.class, List.class);
        doSth.invoke(null, 2, new ArrayList<Integer>() {
            {
                add(1);
                add(3);
                add(5);
                add(7);
            }
        });
    }

    public static void doSth(int xx, List<Integer> integers) {
        log.info("lalala [{}], [{}]", xx, integers);
    }

}
