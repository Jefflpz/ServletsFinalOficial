FROM maven:3.8.3-openjdk-17 AS build
# Define o diretório de trabalho
WORKDIR /app
# Copia o arquivo pom.xml e as dependências necessárias para o diretório de trabalho
COPY . .
# Executa o comando Maven para construir o projeto
RUN mvn clean package -DskipTests
# Etapa 2: Imagem de execução com Tomcat
FROM tomcat:10.0.12-jdk17-openjdk-slim
# Remove o aplicativo padrão do Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*
# Copia o arquivo WAR do build para o diretório webapps do Tomcat
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
# Exponha a porta padrão do Tomcat
EXPOSE 8080
# Inicia o Tomcat
CMD ["catalina.sh", "run"]
