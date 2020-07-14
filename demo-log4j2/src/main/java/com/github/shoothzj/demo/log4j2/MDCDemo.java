package com.github.shoothzj.demo.log4j2;

import org.slf4j.MDC;

/**
 * @author hezhangjian
 */
public class MDCDemo {

    public static void main(String[] args) {
        MDC.put("deed", "dede");
    }

}
