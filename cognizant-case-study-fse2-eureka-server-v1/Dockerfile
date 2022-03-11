FROM java:8
EXPOSE 9050
ADD /target/*.jar eureka.jar
ENTRYPOINT ["java","-jar","eureka.jar"]