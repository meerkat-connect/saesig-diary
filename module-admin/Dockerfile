#FROM openjdk:17-jdk-slim

#COPY gradlew settings.gradle build.gradle /app/
#COPY gradle /app/gradle
#
#COPY module-core/src /app/module-core/src
#COPY module-core/build.gradle /app/module-core/
#
#COPY module-admin/src /app/module-admin/src
#COPY module-admin/build.gradle /app/module-admin/
#
#RUN sed -i 's/\r//' gradlew && chmod +x gradlew
#RUN ./gradlew :module-admin:build -x test --stacktrace
#RUN cp module-admin/build/libs/module-admin-*.jar /app/app.jar
#
#EXPOSE 8080
#
#ENTRYPOINT ["java", "-Dspring.profiles.active=${ACTIVE_PROFILE}", "-Djasypt.encryptorKey=${ENCRYPTOR_KEY}", "-jar", "app.jar"]

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY module-admin/app.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=${ACTIVE_PROFILE}", "-Djasypt.encryptorKey=${ENCRYPTOR_KEY}", "-jar", "app.jar"]

