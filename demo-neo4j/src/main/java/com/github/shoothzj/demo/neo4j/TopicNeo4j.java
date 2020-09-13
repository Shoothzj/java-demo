package com.github.shoothzj.demo.neo4j;

import com.github.shoothzj.demo.neo4j.config.Neo4jConstant;
import com.github.shoothzj.javatool.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;

import java.util.concurrent.TimeUnit;

/**
 * @author hezhangjian
 * 看看能否通过neo4j分析Kafka Topic
 */
@Slf4j
public class TopicNeo4j {

    public static void main(String[] args) {
        Driver driver = GraphDatabase.driver(Neo4jConstant.driverUrl,
                AuthTokens.basic(Neo4jConstant.username, Neo4jConstant.password));
        final Session session = driver.session();
        //创建topic CREATE (<node-name>:<label-name>) {<Property1-name>:<Property1-Value>}
        session.run("CREATE (n: Topic {name: $name})", Values.parameters("name", "data"));
        session.run("CREATE (n: Producer {name: $name})", Values.parameters("name", "IoCM"));
        session.run("CREATE (n: Consumer {name: $name})", Values.parameters("name", "IoDM"));
        //创建关系
        {
            //MATCH (<node1-label-name>:<node1-name>),(<node2-label-name>:<node2-name>)
            //CREATE
            //	(<node1-label-name>)-[<relationship-label-name>:<relationship-name>]->(<node2-label-name>)
            //RETURN <relationship-label-name>
            final Result result = session.run("MATCH (t:Topic),(p:Producer) WHERE t.name=\"data\" AND p.name=\"IoCM\" CREATE (p)-[r:produce]->(t) RETURN r");
            log.info(result.toString());
        }
        CommonUtil.sleep(TimeUnit.SECONDS, 3);
        driver.close();
    }

}
