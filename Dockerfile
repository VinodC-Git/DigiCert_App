FROM eclipse-temurin:11-jdk

LABEL mentainer="netpractice123@gmail.com"

WORKDIR /app

COPY ./target/digicert_app-1.0.0-SNAPSHOT.jar /app

CMD ["java", "-jar", "digicert_app-1.0.0-SNAPSHOT.jar"]

