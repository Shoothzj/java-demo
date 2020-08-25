package com.github.shoothzj.demo.metrics;

import com.github.shoothzj.demo.base.module.ShellResult;
import com.github.shoothzj.demo.base.util.LogUtil;
import com.github.shoothzj.demo.base.util.ShellUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author hezhangjian
 */
@Slf4j
public class DiskUtilizationMetrics {

    private static final ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

    private static long lastTime = -1;

    public static void main(String[] args) {
        LogUtil.configureLog();
        String diskName = "vda1";
        scheduledExecutor.scheduleAtFixedRate(() -> metrics(diskName), 0, 10, TimeUnit.SECONDS);
    }

    private static void metrics(String diskName) {
        //假设统计vda磁盘
        String[] cmd = {
                "/bin/bash",
                "-c",
                "cat /proc/diskstats |grep " + diskName + "|awk '{print $13}'"
        };
        ShellResult shellResult = ShellUtil.executeCmd(cmd);
        String timeStr = shellResult.getInputContent().substring(0, shellResult.getInputContent().length() - 1);
        long time = Long.parseLong(timeStr);
        if (lastTime == -1) {
            log.info("first time cal, usage time is [{}]", time);
        } else {
            double usage = (time - lastTime) / (double) 10_000;
            log.info("usage time is [{}]", usage);
        }
        lastTime = time;
    }

}
