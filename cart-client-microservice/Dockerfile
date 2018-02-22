FROM anapsix/alpine-java:8_jdk_unlimited
#FROM underwaterpony/centos
#FROM primetoninc/jdk:1.8
MAINTAINER  <venkat@nikhu.com>

RUN apk add --update curl && \
    rm -rf /var/cache/apk/*
    
ADD ./build/libs/cart-client-microservice-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx1024m", "-jar", "/app/cart-client-microservice-0.0.1-SNAPSHOT.jar"]

EXPOSE 9012

#docker build . -t ecommerce/cart-client-microservice
#docker network create my_nw
#docker run --rm -p 9012:9012 --name cart-client-microservice --link configuration-microservice --network my_nw ecommerce/cart-client-microservice
