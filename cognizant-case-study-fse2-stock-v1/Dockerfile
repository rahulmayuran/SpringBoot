FROM mcr.microsoft.com/java/jre-headless:11u9-zulu-alpine
EXPOSE 9051
ADD /target/*.jar stock.jar
ENTRYPOINT ["java","-jar","stock.jar"]