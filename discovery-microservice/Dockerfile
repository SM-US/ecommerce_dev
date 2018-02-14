FROM anapsix/alpine-java:8_jdk_unlimited
MAINTAINER Codiez <codiez@devloper.com>

RUN apk add --update curl && \
    rm -rf /var/cache/apk/*

ADD ./build/libs/discovery-microservice-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/discovery-microservice-0.0.1-SNAPSHOT.jar"]

EXPOSE 8001

#docker build . -t ecommerce/discovery_microservice
#docker network create my_nw
#docker run --rm -p 8001:8001 --name discovery-microservice image_discovery_microservice