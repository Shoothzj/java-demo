package com.github.shoothzj.demo.flink;

import com.github.shoothzj.demo.flink.module.WordWithCount;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * @author hezhangjian
 */
@Slf4j
public class StreamingJob {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // listen local 9000
        DataStream<String> text = env.socketTextStream("127.0.0.1", 9000, "\n");
        
        // 将接收到的数据拆分，分组，窗口计算并聚合输出
        SingleOutputStreamOperator<WordWithCount> windowCounts = text.flatMap(new FlatMapFunction<String, WordWithCount>() {
            @Override
            public void flatMap(String value, Collector<WordWithCount> out) throws Exception {
                String[] words = value.split("\\s");
                for (String word : words) {
                    out.collect(new WordWithCount(word, 1L));
                }
            }
        }).keyBy("word").timeWindow(Time.seconds(5), Time.seconds(1))
                .reduce(new ReduceFunction<WordWithCount>() {
                    @Override
                    public WordWithCount reduce(WordWithCount value1, WordWithCount value2) throws Exception {
                        return null;
                    }
                });
        windowCounts.print().setParallelism(1);

        env.execute("Socket Window WordCount");
    }

}
