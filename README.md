
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

## Instruções

Essa é a branch dev, o projeto está configurado para roda em localhost com o banco de dados de memória local H2 porém não persistindo os dados. 

Para utilizar o projeto basta abrir cada microsserviço em sua IDE que compile JAVA, e rodar na seguinte ordem:

- Importe o arquivo "api_endpoints_documentation.json" para seu software de requisições HTTP (Postman, Insomnia, etc)
- Para o serviço de mensageria funcionar será necessário criar uma imagem docker com o "Dockerfile" da pasta "rabbitmq" e depois conteinerizar-la para as portas padrões "-p 5672:5672 -p 15672:15672"
- Abra e execute o serviço "eureka-server"
- Abra e execute o serviço "ms-employee"
- Abra e execute o serviço "ms-proposal"
- Abra e execute o serviço "ms-voting"
- Abra e execute o serviço "gateway"
- Execute os endpoints no seu software de requisições HTTP

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
