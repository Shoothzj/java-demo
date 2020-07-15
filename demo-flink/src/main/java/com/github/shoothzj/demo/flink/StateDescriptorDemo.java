package com.github.shoothzj.demo.flink;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.StateTtlConfig;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @author hezhangjian
 */
@Slf4j
public class StateDescriptorDemo {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.fromElements(Tuple2.of(1L, 3L), Tuple2.of(1L, 3L), Tuple2.of(1L, 3L), Tuple2.of(1L, 3L), Tuple2.of(1L, 3L))
                .keyBy(0)
                .flatMap(new CountWindowAverage())
                .printToErr();
        env.execute("submit job");
    }

    public static class CountWindowAverage extends RichFlatMapFunction<Tuple2<Long, Long>, Tuple2<Long, Long>> {

        private transient ValueState<Tuple2<Long, Long>> sum;

        @Override
        public void flatMap(Tuple2<Long, Long> input, Collector<Tuple2<Long, Long>> out) throws Exception {
            Tuple2<Long, Long> currentSum;
            if (sum.value() == null) {
                currentSum = Tuple2.of(0L, 0L);
            } else {
                currentSum = sum.value();
            }

            currentSum.f0 += 1;
            currentSum.f1 += input.f1;

            sum.update(currentSum);

            if (currentSum.f0 >= 2) {
                out.collect(new Tuple2<>(input.f0, currentSum.f1 / currentSum.f0));
                sum.clear();
            }
        }

        @Override
        public void open(Configuration parameters) throws Exception {
            ValueStateDescriptor<Tuple2<Long, Long>> descriptor =
                    new ValueStateDescriptor<Tuple2<Long, Long>>("average",
                            TypeInformation.of(new TypeHint<Tuple2<Long, Long>>() {
                            }));
            StateTtlConfig ttlConfig = StateTtlConfig.newBuilder(Time.seconds(10)).setUpdateType(StateTtlConfig.UpdateType.OnCreateAndWrite)
                    .setStateVisibility(StateTtlConfig.StateVisibility.NeverReturnExpired)
                    .build();
            descriptor.enableTimeToLive(ttlConfig);
            sum = getRuntimeContext().getState(descriptor);
        }
    }

}
