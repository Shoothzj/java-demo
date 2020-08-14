cd "$(dirname "$0")"

cd ..

echo `pwd`

java -Dlog4j.configurationFile=/opt/sh/scripts/log4j2.xml -jar /opt/sh/ttbb.jar