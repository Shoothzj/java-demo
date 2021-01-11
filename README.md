## LocalTest
```bash
java -Xmx512M -Xms512M -XX:MaxDirectMemorySize=32M -jar build/libs/ttbb.jar
```
### Performance Pulsar
```bash
-Dpulsar.send.delay.ms=0 -Dpulsar.addr=http://10.0.0.62:8080
```