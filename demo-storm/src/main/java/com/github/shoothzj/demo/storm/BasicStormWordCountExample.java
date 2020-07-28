package com.github.shoothzj.demo.storm;

import com.github.shoothzj.demo.base.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

import java.util.concurrent.TimeUnit;

/**
 * @author hezhangjian
 */
@Slf4j
public class BasicStormWordCountExample {

    public static void main(String[] args) throws Exception {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("sentence-spout", new FixedSentenceSpout());
        builder.setBolt("split-bolt", new SplitSentenceBolt()).shuffleGrouping("sentence-spout");
        builder.setBolt("count-bolt", new CountWordBolt()).fieldsGrouping("split-bolt", new Fields("word"));
        builder.setBolt("display-bolt", new DisplayBolt()).globalGrouping("count-bolt");

        Config config = new Config();

        //Local Cluster
        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("word-count-topology", config, builder.createTopology());
        CommonUtil.sleep(TimeUnit.SECONDS, 20);
        localCluster.killTopology("word-count-topology");
        localCluster.shutdown();
    }

}
