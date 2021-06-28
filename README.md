# Marketplace challenge

Desafio de desenvolvimento de uma aplicação de Marketplace utilizando Java.

## Descrição

Foi utilizado o framework spring boot com o banco de dados em memório H2.
O ranqueamento de produtos é executado todos os dias às 1:00h, podendo ser executado
manualmente acessando a API: http://localhost:8080/rank/execute


## Pré requisitos
* Apache Maven instalado e configurado.

## Executando a aplicação
Para execução da aplicação, na raiz do projeto executar os comandos abaixo:
```
mvn clean install
```
```
mvn spring-boot:run
```
Outra forma é a execução é pela IDE.
Execute o método main da classe abaixo:
```
com.marketplace.marketplacechallenge.MarketplaceChallengeApplication.main
```
### Dependências

* Spring boot V2.5.2
* H2 Database
* Spring Data JPA
* Spring Security
* Flyway Migration
* Spring Data JDBC
* Mockito
* Swagger

### Acessando as APIs

Para ter acesso as APIs acesse a URL abaixo:
```
http://localhost:8080/swagger-ui.html
```


Será exibido uma página com todos os serviços disponíveis.

Entre com as credenciais:
```
user: admin
password: admin
```

Para acessar o console do banco de dados H2, acesse:
```
http://localhost:8080/h2-console
```
Entre com as credencias:
```
user: sa
password: sa
```

## Autor
[João Otávio Rezende](jotaviorezende@gmail.com)

## Version History

* 0.1
    * Initial Release
