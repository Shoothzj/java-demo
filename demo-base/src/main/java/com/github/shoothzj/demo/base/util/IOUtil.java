package com.github.shoothzj.demo.base.util;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class IOUtil extends IOUtils{

    private static final Logger log = LoggerFactory.getLogger(IOUtil.class);

    public static String readFile2String(String fileName) {
        try {
            return read2String(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            log.error("file not found exception is {}", e);
            return "";
        }
    }

    public static String read2String(InputStream inputStream) {
        return read2StringCharset(inputStream, "utf-8");
    }

    public static String read2StringCharset(InputStream inputStream, String charset) {
        try {
            return new String(read2Byte(inputStream), charset);
        } catch (UnsupportedEncodingException e) {
            log.error("UnSupport encoding error error, exception is {}", e);
            return "";
        }
    }

    public static byte[] read2Byte(InputStream inputStream) {
        try {
            byte[] bytes = readFully(new BufferedInputStream(inputStream), inputStream.available());
            return bytes;
        } catch (java.io.IOException e) {
            log.error("Read File error, exception is {}", e);
        }
        return new byte[0];
    }

}
