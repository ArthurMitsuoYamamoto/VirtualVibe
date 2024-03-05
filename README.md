# VirtualVibe
API do projeto VirtualVibe - controle de Jogos

## Tarefas
- [ ] CRUD de Playstation games
- [ ] CRUD de Xbox games
- [ ] CRUD de Nintendo games

## Documentação da API

### Endpoint
- [Listar Todas os PS games](#Listar-todos-os-PS-games)
-[Cadastrar PS games](#Cadastrar-PS-games)
-[Detalhes de PS game](#detalhes-de-PS-game)
-[Apagar PS games](#apagar-PS-game)
-[Atualizar PS game](#atualizar-PS-game)


### Listar Todas os PS Games

`GET` /ps-games


Retorna um array com todos os games cadastrados.

#### Exemplo de Resposta

``` js
[
    {
        "id": 1,
        "titulo": "Minecraft",
        "preco" : "R$150,00",
        "descricao: "Minecraft é um jogo em que você constrói coisas com blocos, em um mundo virtual aberto e livre para explorar. É o que os gamers chamam de 'sandbox', porque funciona mesmo como uma caixa de areia, em que o único limite para o jogador é a própria imaginação e a prática",

    }
]

```

#### Códigos de Status

|código|descrição|
|------|---------|
|200| Os dados dos games foram retornados com sucecesso
|401|Acesso negado. Você deve se autenticar

---

### Cadastrar PS GAME

`POST` /ps-games


Cria um novo game com os dados enviados no corpo da requisição

#### Corpo da Requisição

|campo|tipo|obrigatório|descrição|
|-----|----|:-----------:|---------|
|titulo|string|✅|Titulo do game
|preco|number|✅|Preco do game (representado como numero decimal)
|descricao|string|✅|Um breve sinopse do game


``` js
{
    "titulo": "Minecraft",
    "preco" : "R$150,00",
    "descricao: "Minecraft é um jogo em que você constrói coisas com blocos, em um mundo virtual aberto e livre para explorar. É o que os gamers chamam de 'sandbox', porque funciona mesmo como uma caixa de areia, em que o único limite para o jogador é a própria imaginação e a prática"
}
```

#### Exemplo da Resposta


``` js
{
    "id": 1,
    "titulo": "Minecraft",
    "preco" : "R$150,00",
    "descricao: "Minecraft é um jogo em que você constrói coisas com blocos, em um mundo virtual aberto e livre para explorar. É o que os gamers chamam de 'sandbox', porque funciona mesmo como uma caixa de areia, em que o único limite para o jogador é a própria imaginação e a prática"
}


```


#### Códigos de Status

|código|descrição|
|------|---------|
|201|Game cadastrado com sucesso
|400|Dados enviados são inválidos. Verifique o corpo da requisição
|401|Acesso negado. Você deve se autenticar
---



