package com.github.shoothzj.demo.base.pf;

import com.github.shoothzj.javatool.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author hezhangjian
 */
@Slf4j
public class PfThread extends Thread {

    private PfTask pfTask;

    private final LongAdder counter;

    public PfThread(PfTask pfTask) {
        this.pfTask = pfTask;
        this.counter = new LongAdder();
    }

    @Override
    public void run() {
        super.run();
        new Thread(()-> {
            while (true) {
                pfTask.task();
                counter.increment();
            }
        }).start();
        while (true) {
            CommonUtil.sleep(TimeUnit.MINUTES, 1);
            System.out.println(counter);
            counter.reset();
        }
    }

}
