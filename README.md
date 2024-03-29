
#  CHALLENGE 3 - WEEK 12

**Programa de Bolsas  
SP – Backend Journey (Spring Boot 24/07/2023)  
AWS Cloud Context  
CHALLENGE**  

___

"Projeto de microsserviços para uma votação de propostas interna empresarial"

Essa é um projeto utilizando
Java + SpringBoot + OpenFeign + Eureka(+ SpringBoot Security) + SpringCloud Gateway + RabbitMQ
para demonstração de um serviço de cadastro de empregados, cadastro de propostas, votação e mensageria.

## Notas do desenvolvedor

A estrutura do projeto foi a DDD onde o "ms-voting" é um microsserviço escalar, a princípio ele foi desenvolvido para comunicar-se com os microsserviços "ms-employee" e "ms-proposal" requisitando informações dos bancos de cada um e envio de dados(voto).  
O serviço de mensageria funciona quando o serviço "ms-proposal" executa o resultado da votação das propostas.  
Para ter melhor desenvoltura do projeto acredito que deveria ter feito o "ms-voting" para receber o resultado e para votar nas propostas via mensageria, assim o voto ficaria no sistema de mensageria caso o serviço "ms-proposal" caísse.

## Instruções

Essa é a branch main, o projeto está configurado para roda em docker com o banco de dados de memória local H2 persistindo os dados. 

Para utilizar o projeto basta abrir cada pasta dos microsserviços usando o prompt do sistema e executar os comandos:  

- Primeiro criar a network:  
docker network create demattosiury-network  
- Criar a imagem e o conteiner RabbitMQ, dentro da pasta "rabbitmq":  
docker build -t rabbitmq:3.12-management .  
docker run --name rabbitmq -p 5672:5672 -p 15672:15672 --network demattosiury-network rabbitmq:3.12-management  
- Criar a imagem e o conteiner Eureka-Server, dentro da pasta "eureka-server":  
docker build --tag eureka-server-img .  
docker run --name eureka-server-con -p 8761:8761 --network demattosiury-network eureka-server-img  
- Criar a imagem e o conteiner Ms-Employee, dentro da pasta "ms-employee":  
docker build --tag ms-employee-img .  
docker run --name ms-employee-con --network demattosiury-network -e EUREKA_SERVER=eureka-server-con ms-employee-img  
- Criar a imagem e o conteiner Ms-Proposal, dentro da pasta "ms-proposal":  
docker build --tag ms-proposal-img .  
docker run --name ms-proposal-con --network demattosiury-network -e EUREKA_SERVER=eureka-server-con -e RABBITMQ=rabbitmq ms-proposal-img  
- Criar a imagem e o conteiner Ms-Voting, dentro da pasta "ms-voting":  
docker build --tag ms-voting-img .  
docker run --name ms-voting-con --network demattosiury-network -e EUREKA_SERVER=eureka-server-con -e RABBITMQ=rabbitmq ms-voting-img  
- Criar a imagem e o conteiner Gateway, dentro da pasta "gateway":  
docker build --tag gateway-img .  
docker run --name gateway-con -p 8080:8080 --network demattosiury-network -e EUREKA_SERVER=eureka-server-con gateway-img   
- Importe o arquivo "api_endpoints_documentation.json" para seu software de requisições HTTP (Postman, Insomnia, etc)

O serviço de mensageria será mostrado no console do serviço "ms-voting".

### Pré-requisitos

- Java JDK 17
- Docker
- Postman ou Insomnia ou algum software semelhante

## Testes

Os testes automatizados foram executados com cobertura acima de 50%.  

### Documentação API

Os serviços foram documentados utilizando o software Insomnia e gerado a coleção "api_endpoints_documentation.json".

___

Bolsista Iury de Mattos  
iury.mattos.pb@compasso.com.br  

[![Compass UOL](https://stc.uol.com/g/sobreuol/images/para-seu-negocio/compass-logo.svg?v=3.9.44)](https://compass.uol/)
