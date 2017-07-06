FROM frolvlad/alpine-oraclejdk8:slim

ADD emarket-web-site.jar /usr/local/emarket-web-site/

WORKDIR /usr/local/emarket-web-site/

CMD ["java", "-Xms512m", "-Xmx1g", "-jar", "emarket-web-site.jar"]