FROM frolvlad/alpine-oraclejdk8:slim

ADD spring-boot-admin-server.jar /usr/local/spring-boot-admin-server/

WORKDIR /usr/local/spring-boot-admin-server/

CMD ["java", "-Xms512m", "-Xmx1g", "-jar", "spring-boot-admin-server.jar"]