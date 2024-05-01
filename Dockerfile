FROM eclipse-temurin:17-jre-focal

EXPOSE 8080

ARG MYSQL_USERNAME
ARG MYSQL_PASSWORD

ARG BOOK_API_ACCESS_KEY
ARG BOOK_API_SECRET_KEY

ENV MYSQL_USERNAME ${MYSQL_USERNAME}
ENV MYSQL_PASSWORD ${MYSQL_PASSWORD}

ENV BOOK_API_ACCESS_KEY ${BOOK_API_ACCESS_KEY}
ENV BOOK_API_SECRET_KEY ${BOOK_API_SECRET_KEY}

ADD build/libs/devlib-0.0.1-SNAPSHOT.jar devlib-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","devlib-0.0.1-SNAPSHOT.jar"]
