# Estágio de build: usa uma imagem com Gradle e JDK 17 para construir o projeto
FROM gradle:7.6.1-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle build

# Estágio final: usa uma imagem leve com JDK 17 para rodar a aplicação
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copia o arquivo JAR gerado no estágio de build
COPY --from=build /app/build/libs/*.jar app.jar
# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]