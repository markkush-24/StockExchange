FROM openjdk:11-jdk-slim
RUN apk add curl
VOLUME /tmp
EXPOSE 8080
COPY build/libs/stockexchange-aws-deploy-service.jar stockexchange-aws-deploy-service.jar
ENTRYPOINT ["java","-jar","/stockexchange-aws-deploy-service.jar"]