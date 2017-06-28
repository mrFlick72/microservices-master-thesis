FROM frolvlad/alpine-oraclejdk8:slim

ADD turbine-stream-server.jar /usr/local/turbine-stream-server/

WORKDIR /usr/local/turbine-stream-server/

CMD ["java", "-Xms512m", "-Xmx1g", "-jar", "turbine-stream-server.jar"]