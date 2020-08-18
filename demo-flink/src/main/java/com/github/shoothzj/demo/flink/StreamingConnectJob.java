package com.github.shoothzj.demo.flink;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoProcessFunction;
import org.apache.flink.util.Collector;

import java.util.List;
import java.util.Map;

/**
 * @author hezhangjian
 */
@Slf4j
public class StreamingConnectJob {

    public static void main(String[] args) throws Exception{
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DataStreamSource<Tuple2<Integer, Long>> one = env.fromElements(
                //浏览记录
                Tuple2.of(1, 250L),
                Tuple2.of(2, 251L),
                Tuple2.of(3, 252L),
                Tuple2.of(4, 253L)
        );
        DataStreamSource<Tuple2<Integer, String>> two = env.fromElements(
                //浏览记录
                Tuple2.of(1, "one"),
                Tuple2.of(2, "two"),
                Tuple2.of(3, "three"),
                Tuple2.of(4, "four")
        );
        ConnectedStreams<Tuple2<Integer, Long>, Tuple2<Integer, String>> connect = one.connect(two);
        SingleOutputStreamOperator<String> streamOperator = connect.process(new CoProcessFunction<Tuple2<Integer, Long>, Tuple2<Integer, String>, String>() {
            @Override
            public void processElement1(Tuple2<Integer, Long> value, Context ctx, Collector<String> out) throws Exception {
                out.collect("data 1" + value.getField(0) + "value 1: " + value.getField(1));
            }

            @Override
            public void processElement2(Tuple2<Integer, String> value, Context ctx, Collector<String> out) throws Exception {
                out.collect("data 2" + value.getField(0) + "value 1: " + value.getField(1));
            }
        });
        streamOperator.print();
        env.execute("execute cep");
    }

}
