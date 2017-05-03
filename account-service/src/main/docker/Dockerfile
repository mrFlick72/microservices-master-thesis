FROM frolvlad/alpine-oraclejdk8:slim

ADD account-service.jar /usr/local/account-service/

WORKDIR /usr/local/account-service/

EXPOSE 8080

CMD ["java", "-Xms512m", "-Xmx1g", "-jar", "account-service.jar"]