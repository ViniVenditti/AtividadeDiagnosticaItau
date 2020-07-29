# Exercicio MasterTech - Atividade Diagnostica

Projeto para teste de nivelamento do curso ministrado pela Mastertech

Nesse exercício foi utilizado as seguintes tecnologias:

* Java 8
* Spring Boot (2.1.5.RELEASE)
* Spring Data
* H2

Requisições abaixo foram feitas a partir de **http://localhost:8080**

## Api de Usuário
### GET Listar usuarios 
#### /usuario
Exemplo de response:
```
[
    {
        "id": 1,
        "nome": "Vinicius Venditti",
        "cpf": "40075403811",
        "email": "vinicius@teste.com",
        "dataCadastro": "2020-07-29"
    },
    {
        "id": 2,
        "nome": "Ronaldinho Gaucho",
        "cpf": "99988546812",
        "email": "ronaldinho@teste.com",
        "dataCadastro": "2020-07-29"
    },
    {
        "id": 3,
        "nome": "Lionel Messi",
        "cpf": "55485632149",
        "email": "messi@teste.com",
        "dataCadastro": "2020-07-29"
    }
]
```

### GET Listar apenas usuário especifico
#### /usuario/{idUsuario}
```
{
    "id": 1,
    "nome": "Vinicius Venditti",
    "cpf": "40075403811",
    "email": "vinicius@teste.com",
    "dataCadastro": "2020-07-29"
}
```

### POST Cadastrar um usuário
#### /usuario
exemplo de body
```
{
	"nome" :"Lionel Messi",
	"cpf" : "55485632149",
	"email" : "messi@teste.com"
}
```
Response
```
{
    "id": 3,
    "nome": "Lionel Messi",
    "cpf": "55485632149",
    "email": "messi@teste.com",
    "dataCadastro": "2020-07-29"
}
```
###PUT Editar usuário
####/usuario/{idUsuario}
Exemplo de body
```
{
    "nome": "Vinicius Fermino"
}
```
response
```
{
    "id": 1,
    "nome": "Vinicius Fermino",
    "cpf": "40075403811",
    "email": "vinicius@teste.com",
    "dataCadastro": "2020-07-29"
}
```
##API de Ponto
### POST Bater Ponto
####/ponto
Exemplo Body
```
{
    "tipoBatida": "ENTRADA",
    "usuarioModel": {
        "id": 1
    }
}
```
Response
```
{
    "ponto_id": 4,
    "horario": "2020-07-29T12:34:18.3483208",
    "tipoBatida": "ENTRADA"
}
```
###GET Relatório por usuário
####/ponto/{idUsuario}
Api Get, não existe body

Response
```
{
    "totalHoras": 1,
    "usuario": {
        "usuario_id": 1,
        "nomeCompleto": "Vinicius Fermino",
        "cpf": "40075403811",
        "email": "vinicius@teste.com",
        "dataCadastro": "29/07/2020",
        "pontos": [
            {
                "ponto_id": 4,
                "horario": "2020-07-29T12:34:18.348321",
                "tipoBatida": "ENTRADA"
            },
            {
                "ponto_id": 5,
                "horario": "2020-07-29T13:36:58.226204",
                "tipoBatida": "SAIDA"
            }
        ]
    }
}
```
#####Observação
A API for desenvolvida para registrar o ponto com a hora atual no momento do registro.