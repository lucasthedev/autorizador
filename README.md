# Desafio Caju Backend

Serviço para registro de transações financeiras.

## Especificações técnicas do projeto

### 📋 Ferramentas utilizadas

 * Java 21
 * Maven
 * Docker-compose
 * PostgreSQL
 * Flyway
 * Swagger
 * JUnit
 * Mockito
 * Redis


## 🔧 Passos para execução do projeto

Executar o seguinte comando no direteório raíz do projeto 
para subir o banco de dados:
```
docker-compose up
```
Após verificado que o banco está em funcionamento, realizar o build do projeto com maven:
```
mvn clean install
```
Para que as tabelas do banco de dados sejam criadas, é necessário executar o seguinte comando:
```
mvn flyway:migrate
```
Para visualizar a documentação dos end-points, com a aplicação em execução, acesse no navegador:
```
http://localhost:8080/swagger-ui/index.html
```