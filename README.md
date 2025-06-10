# Integrantes
- Leonardo Louzada Melo - UC23100791
- Luiz Guilherme Oliveira da Mota - UC23100095
- Mateus Bolivar Vieira Duarte - UC22201246
- Yago Mello Mesquita - UC23100428

## Para criar, editar ou deletar o cliente, loja e entrgador
Ainda está sendo necessária a utilização do postman passando os endpoints na url par fazer a requisição pelo Body, utilizando raw passando um `JSON`.

O token está sendo acionado na área do Header, utilizano o Authorization e passando um token Bearer

estrutura do token: tipo do token, corpo do token (id, email) e na assinatura vai a `SECRET_KEY`, que é:
```java
private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
```

Para gerar a estrutura do token é necessária uma claim, as claims são pares chave-valor que transmitem dados sobre o usuário ou sobre o próprio token, dessa forma que se inicia o token no contexto JWT.

Em teoria o cliente não precisa criar otoken, o token é gerado quando o cliente logar, utilizando uma chave Auth passando o Baerer<token>.

## Fluxo

- 1. Acessar o método de `criarCliente(ClienteDTO)` para criar o cliente
- 2. Depois acessar o método `validarLogin()`, passando o email e a senha do cliente obedecendo os parametros: Considerando os dois decorators sobre a senha
```java
private String emailCliente;
private String senhaCliente; 
```
- 3. Após isso poderá chamar qualquer um dos metódos CRUD que envolvam o cliente.

- __O fluxo está disponivel para teste para as três classes principais, loja, cliente e entregador.__

## Documentação da API

A documentação completa da API (Swagger/OpenAPI) está em:

[https://app.swaggerhub.com/apis/hortifood/Hortifood2/1.0.0](https://app.swaggerhub.com/apis/hortifood/Hortifood2/1.0.0)

