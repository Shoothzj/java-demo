package com.github.shoothzj.demo.storm;

import lombok.extern.slf4j.Slf4j;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * @author hezhangjian
 */
@Slf4j
public class FixedSentenceSpout extends BaseRichSpout {

    private SpoutOutputCollector collector;

    private String[] sentences = {
            "This is example of chapter 4",
            "This is word count example",
            "Very basic example of Apache Storm",
            "Apache Storm is open source real-time processing engine",
    };

    private int index = 0;

    @Override
    public void open(Map<String, Object> conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void nextTuple() {
        String sentence = sentences[index];
        log.info(sentence);
        this.collector.emit(new Values(sentence));
        index++;
        if (index >= sentence.length()) {
            index = 0;
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("sentence"));
    }
}
