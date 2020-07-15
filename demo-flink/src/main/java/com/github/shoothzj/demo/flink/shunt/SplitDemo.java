package com.github.shoothzj.demo.flink.shunt;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SplitStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hezhangjian
 */
@Slf4j
public class SplitDemo {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 获取数据源
        ArrayList<Tuple3<Integer, Integer, Integer>> data = new ArrayList<>();
        data.add(new Tuple3<>(0, 1, 0));
        data.add(new Tuple3<>(0, 1, 1));
        data.add(new Tuple3<>(0, 2, 2));
        data.add(new Tuple3<>(0, 1, 3));
        data.add(new Tuple3<>(1, 2, 9));
        data.add(new Tuple3<>(1, 2, 11));
        data.add(new Tuple3<>(1, 2, 13));

        DataStreamSource<Tuple3<Integer, Integer, Integer>> items = env.fromCollection(data);

        SplitStream<Tuple3<Integer, Integer, Integer>> splitStream = items.split(new OutputSelector<Tuple3<Integer, Integer, Integer>>() {
            @Override
            public Iterable<String> select(Tuple3<Integer, Integer, Integer> value) {
                List<String> tags = new ArrayList<>();
                if (value.f0 == 0) {
                    tags.add("zeroStream");
                } else if (value.f0 == 1) {
                    tags.add("oneStream");
                }
                return tags;
            }
        });
        splitStream.select("zeroStream").print();
        splitStream.select("oneStream").printToErr();

        env.execute("user defined streaming source");
    }

}
