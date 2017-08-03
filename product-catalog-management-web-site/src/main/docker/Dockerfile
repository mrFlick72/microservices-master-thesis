FROM frolvlad/alpine-oraclejdk8:slim

ADD product-catalog-management-web-site.jar /usr/local/product-catalog-management-web-site/

WORKDIR /usr/local/product-catalog-management-web-site/

CMD ["java", "-Xms512m", "-Xmx1g", "-jar", "product-catalog-management-web-site.jar"]