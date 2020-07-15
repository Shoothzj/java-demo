package com.github.shoothzj.demo.flink.module;

/**
 * @author hezhangjian
 */
public class WordWithCount {

    public String word;

    public long frequency;

    public WordWithCount() {

    }

    public WordWithCount(String word, long frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "WordWithCount{" +
                "word='" + word + '\'' +
                ", count=" + frequency +
                '}';
    }
}
