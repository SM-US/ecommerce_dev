FROM anapsix/alpine-java:8_jdk_unlimited
#FROM underwaterpony/centos
#FROM primetoninc/jdk:1.8
MAINTAINER Venkata Ravuri <venkat@nikhu.com>

RUN apk add --update curl && \
    rm -rf /var/cache/apk/*
    
ADD ./build/libs/cart-microservice-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx1024m", "-jar", "/app/cart-microservice-0.0.1-SNAPSHOT.jar"]

EXPOSE 9002

#docker build . -t ecommerce/cart-microservice
#docker run --rm -p 9002:9002 --name cart-microservice --link redis:redis --link discovery-microservice ecommerce/cart-microservice
