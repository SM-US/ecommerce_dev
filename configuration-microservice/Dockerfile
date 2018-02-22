FROM anapsix/alpine-java:8_jdk_unlimited
MAINTAINER Venkata Ravuri <venkat@nikhu.com>

RUN apk add --update curl && \
    rm -rf /var/cache/apk/*

ADD ./build/libs/configuration-microservice-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/configuration-microservice-0.0.1-SNAPSHOT.jar"]

EXPOSE 8002

#docker build . -t ecommerce/configuration-microservice
#docker network create my_nw
#docker run --rm -p 8002:8002 --name configuration-microservice --link discovery-microservice --network my_nw ecommerce/configuration-microservice

