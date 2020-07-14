package com.github.shoothzj.javatool.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionUtil {

    public static String getException(Exception e) {
        StringBuilder builder = new StringBuilder();
        StackTraceElement[] trace = e.getStackTrace();
        for (StackTraceElement traceElement : trace) {
            builder.append("\tat ").append(traceElement).append("\n");
        }

        return builder.toString();
    }

    /**
     * 打印当前线程的调用堆栈
     */
    void printTrack() {
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        StringBuilder sbf = new StringBuilder();
        for (StackTraceElement e : st) {
            if (sbf.length() > 0) {
                sbf.append(" <- ");
                sbf.append(System.getProperty("line.separator"));
            }
            sbf.append(java.text.MessageFormat.format("{0}.{1}() {2}"
                    , e.getClassName()
                    , e.getMethodName()
                    , e.getLineNumber()));
        }
        log.info(sbf.toString());
    }

}
