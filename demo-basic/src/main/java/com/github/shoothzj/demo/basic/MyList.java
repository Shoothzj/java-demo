package com.github.shoothzj.demo.basic;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @author hezhangjian
 */
@Slf4j
public class MyList<E> extends ArrayList<E> {

    @Override
    public String toString() {
        return "MyList: " + super.toString();
    }
}
