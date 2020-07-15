package com.github.shoothzj.demo.flink;

import com.github.shoothzj.demo.flink.module.WordWithCount;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.BatchTableEnvironment;

import java.util.ArrayList;

/**
 * @author hezhangjian
 */
@Slf4j
public class WordCountSQL {

    public static void main(String[] args) throws Exception {
        ExecutionEnvironment fbEnv = ExecutionEnvironment.getExecutionEnvironment();
        BatchTableEnvironment fbTableEnv = BatchTableEnvironment.create(fbEnv);

        String words = "hello flink hello lagou";
        String[] split = words.split("\\W+");
        ArrayList<WordWithCount> list = new ArrayList<>();

        for (String word : split) {
            WordWithCount wordWithCount = new WordWithCount(word, 1);
            list.add(wordWithCount);
        }
        DataSet<WordWithCount> input = fbEnv.fromCollection(list);
        // convert dataset to table
        Table table = fbTableEnv.fromDataSet(input, "word,frequency");
        table.printSchema();

        // register a table
        fbTableEnv.createTemporaryView("WordCount", table);

        Table table02 = fbTableEnv.sqlQuery("SELECT word AS word, sum(frequency) AS frequency FROM WordCount GROUP BY word");

        DataSet<WordWithCount> ds3 = fbTableEnv.toDataSet(table02, WordWithCount.class);
        ds3.printToErr();
    }

}
