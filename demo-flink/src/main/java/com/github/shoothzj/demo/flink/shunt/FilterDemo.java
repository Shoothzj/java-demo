package com.github.shoothzj.demo.flink.shunt;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;

/**
 * @author hezhangjian
 */
@Slf4j
public class FilterDemo {

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
        SingleOutputStreamOperator<Tuple3<Integer, Integer, Integer>> zeroStream = items.filter((FilterFunction<Tuple3<Integer, Integer, Integer>>) value -> value.f0 == 0);
        SingleOutputStreamOperator<Tuple3<Integer, Integer, Integer>> oneStream = items.filter((FilterFunction<Tuple3<Integer, Integer, Integer>>) value -> value.f0 == 1);

        zeroStream.print();
        oneStream.printToErr();

        //打印结果
        String jobName = "user defined streaming source";
        env.execute(jobName);
    }

}
