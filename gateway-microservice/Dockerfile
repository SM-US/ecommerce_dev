FROM anapsix/alpine-java:8_jdk_unlimited
MAINTAINER Venkata Ravuri <venkat@nikhu.com>

RUN apk add --update curl && \
    rm -rf /var/cache/apk/*

ADD ./build/libs/gateway-microservice-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx1024m", "-jar", "/app/gateway-microservice-0.0.1-SNAPSHOT.jar"]

EXPOSE 8000

#docker build . -t gateway-microservice/image_gateway-microservice
#docker network create my_nw
#docker run --rm -p 8000:8000 --name gateway-microservice --link discovery-microservice --network my_nw gateway-microservice/image_gateway-microservice
