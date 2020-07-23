package com.github.shoothzj.demo.flink;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

/**
 * @author hezhangjian
 */
@Slf4j
public class StartEndDuration extends KeyedProcessFunction<String, Tuple2<String, String>, Tuple2<String, Long>> {

    private ValueState<Long> startTime;

    @Override
    public void open(Configuration parameters) throws Exception {
        // obtain state handle
        ValueState<Long> startTime = getRuntimeContext()
                .getState(new ValueStateDescriptor<Long>("startTime", Long.class));
    }

    @Override
    public void processElement(Tuple2<String, String> in, Context ctx, Collector<Tuple2<String, Long>> out) throws Exception {
        switch (in.f1) {
            case "START":
                // set the start time if we receive a start event.
                startTime.update(ctx.timestamp());
                // register a timer in four hours from the start event.
                ctx.timerService()
                        .registerEventTimeTimer(ctx.timestamp() + 4 * 60 * 60 * 1000);
                break;
            case "END":
                // emit the duration between start and end event
                Long sTime = startTime.value();
                if (sTime != null) {
                    out.collect(Tuple2.of(in.f0, ctx.timestamp() - sTime));
                    // clear the state
                    startTime.clear();
                }
            default:
                // do nothing
        }
    }

    @Override
    public void onTimer(long timestamp, OnTimerContext ctx, Collector<Tuple2<String, Long>> out) throws Exception {
        // Timeout interval exceeded. Cleaning up the state.
        startTime.clear();
    }
}
