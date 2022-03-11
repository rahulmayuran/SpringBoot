FROM mcr.microsoft.com/java/jre-headless:11u9-zulu-alpine
EXPOSE 9052
ADD /target/*.jar user.jar
ENTRYPOINT ["java","-jar","user.jar"]