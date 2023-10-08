FROM openjdk:11-jdk-slim
RUN apt-get update && apt-get install -y apt && apk add curl
VOLUME /tmp
EXPOSE 8080
COPY build/libs/stockexchange-aws-deploy-service.jar stockexchange-aws-deploy-service.jar
ENTRYPOINT ["java","-jar","/stockexchange-aws-deploy-service.jar"]