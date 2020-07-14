package com.github.shoothzj.demo.basic;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author hezhangjian
 */
@Slf4j
public class ConsumerImpl implements Runnable {

    private String name;

    private List<Integer> data = new MyList<>();

    public ConsumerImpl(String name, int size) {
        this.name = name;
        for (int i = 0; i < size; i++) {
            data.add(i);
        }
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(this.name + ":-----");
            demo();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String demo() {
        return this.name;
    }

}
