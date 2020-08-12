FROM ttbb/base:jdk8

LABEL maintainer="shoothzj@gmail.com"

COPY ttbb.jar /opt/sh/ttbb.jar

COPY docker-scripts /opt/sh/scripts

CMD ["/usr/local/bin/dumb-init", "bash", "-vx","/opt/sh/scripts/start.sh"]
