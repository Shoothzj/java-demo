package com.github.shoothzj.demo.base.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
public class PathUtil {

    public static String pathConnect(String... paths) {
        return Arrays.stream(paths).collect(Collectors.joining(File.separator));
    }

}
