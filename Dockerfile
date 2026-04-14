FROM ghcr.io/graalvm/graalvm-ce:latest AS build

WORKDIR /src
COPY . .
RUN ./gradlew shadowJar --no-daemon -x test

FROM ghcr.io/graalvm/graalvm-ce:latest
WORKDIR /app
COPY --from=build /src/build/libs/moraysalgovisualizer.jar moraysalgovisualizer.jar

EXPOSE 8080
CMD ["java", "-jar", "moraysalgovisualizer.jar"]

#Folks, please containierize your code sooner than later, You will thank yourself.