FROM openjdk:17-alpine

# for caching optimisations
#COPY package*.json /

COPY /target/JwtAuthenticationProject-0.0.1-SNAPSHOT.jar /app/

COPY /target/application.properties /app/

WORKDIR /app


#RUN npm run build

EXPOSE 8080

#CMD ["serve", "-s", "build", "-l", "80"]
CMD ["java", "-jar", "JwtAuthenticationProject-0.0.1-SNAPSHOT.jar"]