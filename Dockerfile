FROM openjdk:19
COPY ./target/DevOps_gp4-0.1.0.1-jar-with-dependencies.jar /tmp/devops
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "DevOps_gp4-0.1.0.1-jar-with-dependencies.jar"]