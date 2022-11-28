FROM adoptopenjdk/openjdk11
ARG JAR_FILE=Microservice_TechCareerAirtiesRegisterLogin-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} register_login.jar
ENTRYPOINT [ "java","-jar","/register_login.jar" ]
