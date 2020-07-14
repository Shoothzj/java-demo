package com.github.shoothzj.javatool.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;

public class StreamUtil {

    private static final Logger log = LoggerFactory.getLogger(StreamUtil.class);

    public static void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception e) {
            log.error("Closable close failed, exception is {}", ExceptionUtil.getException(e));
        }
    }

}
