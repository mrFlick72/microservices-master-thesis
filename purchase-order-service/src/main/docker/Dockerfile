FROM frolvlad/alpine-oraclejdk8:slim

ADD purchase-order-service-server.jar /usr/local/purchase-order-service-server/

WORKDIR /usr/local/purchase-order-service-server/

CMD ["java", "-Xms512m", "-Xmx1g", "-jar", "purchase-order-service-server.jar"]