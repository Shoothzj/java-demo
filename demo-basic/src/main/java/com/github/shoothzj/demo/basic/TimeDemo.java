package com.github.shoothzj.demo.basic;

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author hezhangjian
 */
@Slf4j
public class TimeDemo {

    public static void main(String[] args) {
        System.out.println(Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles")).getTime());
    }

}
