FROM openjdk:19
COPY ./target/classes/devops /tmp/devops
WORKDIR /tmp
ENTRYPOINT ["java", "devops.codereview.App"]