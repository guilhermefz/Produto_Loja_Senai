#Etapa 1 realiza a build

FROM maven:3.9-eclipse-temurin-17 AS build

#Define a pasta de trabalho dentro do container
#Tudo que acontecer daqui pra frente será dentro de /app
WORKDIR /app

#Copia o arquivo pom.xml para dentro do container
COPY pom.xml .

#copia o código fonte (src) do projeto para o container
COPY src ./src

#Executa o build do Maven
#-DskipTests = pula os testes para acelerar o build
#package = gera o arquivo .jar dentro da pasta target
RUN mvn -DskipTests package

#Etapa 2 - imagem final
#Usa a imagem menor só com java JRE
#Não precisa do maven aqui porque o projeto ja foi compilado
FROM eclipse-temurin:17-jre

#Define novamente a pasta de trabalho
WORKDIR /app

#Copia o .jar gerado na estapa BUILD do caminho /app/target para o container final
#Renomeia para o app.jar
COPY --from=build /app/target/*.jar app.jar

#Informa que aplicação usa a porta 8080
EXPOSE 8080

#Comando executado quando o container inicia
#Roda o Spring Boot usando o jar gerado
ENTRYPOINT ["java", "-jar", "app.jar"]