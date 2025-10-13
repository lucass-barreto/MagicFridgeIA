FROM eclipse-temurin:17
LABEL maintainer="lucasbo.dev@gmail.com"
WORKDIR /magicfridgeIA
COPY target/MagicFridgeIA-0.0.1-SNAPSHOT.jar magicfridge.jar
ENTRYPOINT ["java", "-jar", "magicfridge.jar"]