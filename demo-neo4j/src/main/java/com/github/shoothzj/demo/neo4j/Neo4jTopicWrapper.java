package com.github.shoothzj.demo.neo4j;

import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;

/**
 * @author hezhangjian
 */
@Slf4j
public class Neo4jTopicWrapper {

    final Session session;

    public Neo4jTopicWrapper(Session session) {
        this.session = session;
    }

    public void createTopic(String name) {
        session.run("CREATE (n: Topic {name: $name})", Values.parameters("name", name));
    }

    public void createService(String name) {
        session.run("CREATE (n: Service {name: $name})", Values.parameters("name", name));
    }

    public void stateProduce(String serviceName, String topicName) {
        final Result result = session.run("MATCH (t:Topic),(p:Service) WHERE t.name=\"" + topicName + "\" AND p.name=\""
                + serviceName + "\" CREATE (p)-[r:produce]->(t) RETURN r");
    }

    public void stateConsume(String serviceName, String topicName) {
        final Result result = session.run("MATCH (t:Topic),(c:Service) WHERE t.name=\"" + topicName + "\" AND c.name=\""
                + serviceName + "\" CREATE (c)-[r:consume]->(t) RETURN r");
    }

}
