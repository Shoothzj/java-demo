package com.github.shoothzj.demo.flink;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;

/**
 * @author hezhangjian
 */
@Slf4j
public class StreamingAggregationDemo {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // get data source
        ArrayList<Tuple3<Integer, Integer, Integer>> data = new ArrayList<>();
        data.add(new Tuple3<>(0,1,0));
        data.add(new Tuple3<>(0,1,1));
        data.add(new Tuple3<>(0,2,2));
        data.add(new Tuple3<>(0,1,3));
        data.add(new Tuple3<>(1,2,5));
        data.add(new Tuple3<>(1,2,9));
        data.add(new Tuple3<>(1,2,11));
        data.add(new Tuple3<>(1,2,13));

        DataStreamSource<Tuple3<Integer, Integer, Integer>> items = env.fromCollection(data).setParallelism(1);
        items.keyBy(0).maxBy(2).printToErr();

        String jobName = "user defined streaming source";
        env.execute(jobName);
    }

}
