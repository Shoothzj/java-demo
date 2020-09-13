package com.github.shoothzj.demo.neo4j;

import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;

/**
 * @author hezhangjian
 */
@Slf4j
public class Neo4jSeparateWrapper {

    final Session session;

    public Neo4jSeparateWrapper(Session session) {
        this.session = session;
    }

    public void createTopic(String name) {
        session.run("CREATE (n: Topic {name: $name})", Values.parameters("name", name));
    }

    public void createProducer(String name) {
        session.run("CREATE (n: Producer {name: $name})", Values.parameters("name", name));
    }

    public void createConsumer(String name) {
        session.run("CREATE (n: Consumer {name: $name})", Values.parameters("name", name));
    }

    public void stateProduce(String produceName, String topicName) {
        final Result result = session.run("MATCH (t:Topic),(p:Producer) WHERE t.name=\"" + topicName + "\" AND p.name=\""
                + produceName + "\" CREATE (p)-[r:produce]->(t) RETURN r");
    }

    public void stateConsume(String consumeName, String topicName) {
        final Result result = session.run("MATCH (t:Topic),(c:Consumer) WHERE t.name=\"" + topicName + "\" AND c.name=\""
                + consumeName + "\" CREATE (c)-[r:consume]->(t) RETURN r");
    }

}
