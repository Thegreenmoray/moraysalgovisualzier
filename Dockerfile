FROM ghcr.io/graalvm/graalvm-ce:latest AS build
#where to import your kernel

WORKDIR /src
#the directory you'll use
COPY . .
# first . is where build is, second . is your workdir location
RUN ls -R /src
RUN chmod +x gradlew
RUN ./gradlew bootJar --no-daemon -x test
#RUN ./gradlew shadowJar --no-daemon -x test
# this builds the jar for the next one below v
FROM ghcr.io/graalvm/graalvm-ce:latest

RUN gu install js

WORKDIR /app
COPY --from=build /src/build/libs/moraysalgovisualizer.jar moraysalgovisualizer.jar

EXPOSE 8080
#what port you want to run this to
CMD ["java", "-jar", "moraysalgovisualizer.jar"]
#command to excute the code

#Folks, please containierize your code sooner than later, You will thank yourself.