FROM frolvlad/alpine-oraclejdk8:slim

ADD authentication-server.jar /usr/local/authentication-server/

WORKDIR /usr/local/authentication-server/

CMD ["java", "-Xms512m", "-Xmx1g", "-jar", "authentication-server.jar"]