package com.github.shoothzj.demo.flink.forward;

import com.github.shoothzj.demo.base.service.JacksonService;
import com.github.shoothzj.demo.base.test.module.TestDeviceDto;
import com.github.shoothzj.demo.base.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * @author hezhangjian
 */
@Slf4j
public class FlinkKafka2Mysql {

    public static void main(String[] args) throws Exception {
        LogUtil.configureLog();
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "test");
        SingleOutputStreamOperator<TestDeviceDto> dtoOperator = env
                .addSource(new FlinkKafkaConsumer<>("test_topic", new SimpleStringSchema(), props))
                .map((MapFunction<String, TestDeviceDto>) value -> JacksonService.toObject(value, TestDeviceDto.class));
        dtoOperator.addSink(new SinkToMySQL());
        env.execute("kafka to mysql");
    }

}
