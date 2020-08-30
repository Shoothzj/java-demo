package com.github.shoothzj.demo.base.util;

import com.github.shoothzj.demo.base.test.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author hezhangjian
 */
@Slf4j
public class MessageUtilTest {

    @Test
    public void buildMsg() {
        String messageBuild = MessageUtil.messageBuild(1500);
        System.out.println(messageBuild.getBytes().length);
    }

}
