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

`GET` /categoria

Retorna um array com todos os games cadastrados.

#### Exemplo de Resposta

``` js
[
    {
        "id": 1,
        "titulo": "Minecraft",
        "preco" : "R$150,00",
        "descrição": "Minecraft é um jogo em que você constrói coisas com blocos, em um mundo virtual aberto e livre para explorar. É o que os gamers chamam de 'sandbox', porque funciona mesmo como uma caixa de areia, em que o único limite para o jogador é a própria imaginação e a prática.",
        "publisher: Mojang Studios",
        "Classificação etária: L"

    }
]

```

#### Códigos de Status

|código|descrição|
|------|---------|
|200| Os dados dos games foram retornados com sucecesso
|401|Acesso negado. Você deve se autenticar
