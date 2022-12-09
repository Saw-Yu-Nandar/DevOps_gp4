FROM openjdk:19
COPY Devops_gp4/target/DevOps_gp4-0.1.0.1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "DevOps_gp4-0.1.0.1-jar-with-dependencies.jar"]