FROM maven as builder
WORKDIR /build
COPY mvnw pom.xml ./
RUN mvn verify --fail-never
COPY ./src ./src
RUN mvn clean install -DskipTests
FROM eclipse-temurin:21-jdk-alpine
RUN addgroup --system accounts-group && adduser --system --ingroup accounts-group accounts
USER accounts:accounts-group
WORKDIR accounts
EXPOSE 3001
COPY --from=builder /build/target/*.jar /accounts/app.jar
ENTRYPOINT ["java","-jar","/accounts/app.jar"]