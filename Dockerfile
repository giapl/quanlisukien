
FROM openjdk:17
COPY target/quanlisukien-0.0.1-SNAPSHOT.jar quanlisukien.jar
COPY src/main/resources/firebaseKey.json firebaseKey.json
COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh
ENTRYPOINT ["./wait-for-it.sh", "database:3306","--timeout=20", "--", "java", "-jar", "quanlisukien.jar"]