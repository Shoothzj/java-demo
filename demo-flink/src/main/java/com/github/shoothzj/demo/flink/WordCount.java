package com.github.shoothzj.demo.flink;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.operators.UnsortedGrouping;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * @author hezhangjian
 */
@Slf4j
public class WordCount {

    public static void main(String[] args) throws Exception {
        // create flink env
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // init data set
        DataSet<String> text = env.fromElements(
                "Flink Spark Storm",
                "Flink Flink Flink",
                "Spark Spark Spark",
                "Storm Storm Storm"
        );
        // 通过flink内置的转换函数计算
        AggregateOperator<Tuple2<String, Integer>> counts = text.flatMap(new LineSplitter()).groupBy(0).sum(1);
        counts.printToErr();
    }

    public static final class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {

        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
            String[] tokens = value.toLowerCase().split("\\W+");
            for (String token : tokens) {
                if (token.length() > 0) {
                    out.collect(new Tuple2<String, Integer>(token, 1));
                }
            }
        }
    }

}