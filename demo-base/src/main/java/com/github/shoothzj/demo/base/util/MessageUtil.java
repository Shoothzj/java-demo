package com.github.shoothzj.demo.base.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author hezhangjian
 */
@Slf4j
public class MessageUtil {

    public static final Random random = new Random();

    public static String messageBuild(int messageLength) {
        StringBuilder messageBuilder = new StringBuilder(messageLength);
        for (int i = 0; i < messageLength; i++) {
            messageBuilder.append('a' + random.nextInt(26));
        }
        return messageBuilder.toString();
    }

}
