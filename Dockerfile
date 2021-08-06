
FROM openjdk:12
ADD target/reba-personas.jar ureba-personas.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "reba-personas.jar"]