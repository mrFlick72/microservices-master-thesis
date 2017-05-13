FROM frolvlad/alpine-oraclejdk8:slim

ADD product-catalog-service-server.jar /usr/local/product-catalog-service-server/

WORKDIR /usr/local/product-catalog-service-server/

CMD ["java", "-Xms512m", "-Xmx1g", "-jar", "product-catalog-service-server.jar"]