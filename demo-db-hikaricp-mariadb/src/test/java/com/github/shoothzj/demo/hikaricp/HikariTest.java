package com.github.shoothzj.demo.hikaricp;

import com.github.shoothzj.javatool.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author hezhangjian
 */
@Slf4j
public class HikariTest {

    public static void main(String[] args) {
        while (true) {
            try {
                HikariTool.executeSql("INSERT INTO TTT (name) VALUES ('dea')");
                CommonUtil.sleep(TimeUnit.SECONDS, 1);
            } catch (Exception e) {
                log.error("exception is ", e);
            }
        }
    }

}
