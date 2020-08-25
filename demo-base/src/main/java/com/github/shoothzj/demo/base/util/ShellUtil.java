package com.github.shoothzj.demo.base.util;

import com.github.shoothzj.demo.base.module.ShellResult;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class ShellUtil {

    public static ShellResult executeCmd(String[] cmd) {
        log.info("exec command {}", cmd);
        String inputContent = "";
        String errorContent = "";
        int ret = -1;
        try {
            Process pid = Runtime.getRuntime().exec(cmd);
            ret = pid.waitFor();
            InputStream inputStream = pid.getInputStream();
            InputStream errorStream = pid.getErrorStream();
            inputContent = IOUtil.read2String(inputStream);
            errorContent = IOUtil.read2String(errorStream);
            log.error("command is {}, result is {}, input content is [{}], error content is [{}]"
                    , cmd, ret, inputContent, errorContent);
            return new ShellResult(ret, inputContent, errorContent);
        } catch (Exception e) {
            log.error("Execute command exception. [{}], input content is [{}], error content is [{}]"
                    , e.getMessage(), inputContent, errorContent);
            return new ShellResult(ret, inputContent, errorContent, e);
        }
    }

    public static ShellResult executeCmd(String cmd) {
        log.info("exec command {}", cmd);
        String inputContent = "";
        String errorContent = "";
        int ret = -1;
        try {
            Process pid = Runtime.getRuntime().exec(cmd);
            ret = pid.waitFor();
            InputStream inputStream = pid.getInputStream();
            InputStream errorStream = pid.getErrorStream();
            inputContent = IOUtil.read2String(inputStream);
            errorContent = IOUtil.read2String(errorStream);
            log.error("command is {}, result is {}, input content is [{}], error content is [{}]"
                    , cmd, ret, inputContent, errorContent);
            return new ShellResult(ret, inputContent, errorContent);
        } catch (Exception e) {
            log.error("Execute command exception. [{}], input content is [{}], error content is [{}]"
                    , e.getMessage(), inputContent, errorContent);
            return new ShellResult(ret, inputContent, errorContent, e);
        }
    }

}
