cd "$(dirname "$0")"

cd ..

echo `pwd`

java -Xmx2G -Xms2G -XX:MaxDirectMemorySize=1G -Dlog4j.configurationFile=/opt/sh/scripts/log4j2.xml -jar /opt/sh/ttbb.jar