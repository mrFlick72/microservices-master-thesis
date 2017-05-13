FROM frolvlad/alpine-oraclejdk8:slim

ADD account-service-server.jar /usr/local/account-service-server/

WORKDIR /usr/local/account-service-server/

CMD ["java", "-Xms512m", "-Xmx1g", "-jar", "account-service-server.jar"]