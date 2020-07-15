package com.github.shoothzj.demo.flink;

import com.github.shoothzj.demo.flink.module.Item;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author hezhangjian
 */
@Slf4j
public class StreamingDemo {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // get data source
        DataStreamSource<Item> text = env.addSource(new MyStreamingSource()).setParallelism(1);
        // notice 并行度设置为1
        SingleOutputStreamOperator<Item> item = text.map((MapFunction<Item, Item>) value -> value);
        //print result
        item.print().setParallelism(1);
        String jobName = "user defined streaming source";
        env.execute(jobName);
    }

}
