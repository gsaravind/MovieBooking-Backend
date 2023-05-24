FROM openjdk:17
EXPOSE 8081
ADD target/moviebooking-backend.jar moviebooking-backend.jar
ENTRYPOINT [ "java", "-jar", "/moviebooking-backend.jar" ]