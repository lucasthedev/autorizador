# Autorizador

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
docker-compose up -d
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
## ⚙️ Executando os end-points

No diretório raiz do projeto contém uma collection para importar no postman.

1 - Deve-se primeiro cadastrar uma conta no end-point: http://localhost:8080/accounts com o payload abaixo:
```
{
    "documentNumber": "123456",
    "availableFoodCreditLimit": 1000.00,
    "availableMealCreditLimit": 1000.00,
    "availableCashCreditLimit": 1000.00
}
```

2 - Para registrar as transações deve-se realizar um post para o end-point http://localhost:8080/transactions com o payload abaixo:
```
{
    "accountId": "6289b07e-fae6-42c0-a2d1-c84a71cc24df",
    "amount": 18.22,
    "mcc": "5812",
    "merchant": "PADARIA DO ZE"
}
```
