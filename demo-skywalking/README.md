## OSX运行agent
### http
```
-javaagent:/Users/akka/ShootHzj/apache-skywalking-apm-bin-es7/agent/skywalking-agent.jar -Dskywalking_config=/Users/akka/master/java-demo/demo-skywalking/src/main/resources/http/agent.config -DSW_LOGGING_DIR=/Users/akka/master/java-demo/demo-skywalking
```
### producer
```
-javaagent:/Users/akka/ShootHzj/apache-skywalking-apm-bin-es7/agent/skywalking-agent.jar -Dskywalking_config=/Users/akka/master/java-demo/demo-skywalking/src/main/resources/pulsar/producer/agent.config -DSW_LOGGING_DIR=/Users/akka/master/java-demo/demo-skywalking
```
### consumer
```
-javaagent:/Users/akka/ShootHzj/apache-skywalking-apm-bin-es7/agent/skywalking-agent.jar -Dskywalking_config=/Users/akka/master/java-demo/demo-skywalking/src/main/resources/pulsar/consumer/agent.config -DSW_LOGGING_DIR=/Users/akka/master/java-demo/demo-skywalking
```

## command
### HTTP
```bash
curl -H 'Content-Type:application/json' 'http://localhost:8081' -d '{"msg":"hello"}'
```
### close all
```bash
ps -ef|grep -i apache-skywalking-apm-bin-es7|grep -v grep|awk '{print $2}'|xargs kill -15
```