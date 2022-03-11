FROM mcr.microsoft.com/java/jre-headless:11u9-zulu-alpine
EXPOSE 9053
ADD /target/*.jar company.jar
ENTRYPOINT ["java","-jar","company.jar"]