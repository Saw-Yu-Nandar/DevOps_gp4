FROM openjdk:19
COPY ./target/DevOps_gp4-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "DevOps_gp4-0.1.0.2-jar-with-dependencies.jar", "db:3306", "30000"]