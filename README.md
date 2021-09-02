# Star Wars API

Api Rest Spring Boot para operações com planetas que expoe as funcionalidades:

- Criação manual de um planeta na base de dados local
- Remoção de planeta
- Busca de planeta por ID na base de dados local
- Busca de planeta por nome na base de dados local
- Listagem de todos os planetas cadastrados

__Implantação__
- Implantada (temporariamente) na Google Cloud Plataform, disponível em [https://swapi.dev/](https://swapi.dev/).

- A API se integra com a API pública [https://swapi.dev/](https://swapi.dev/) para obter os filmes onde cada planeta tem aparição.

- Banco de dados noSQL MongoDB implantado na plataforma em nuvem [https://mongodb.com](https://mongodb.com).

- Operações estão documentadas através da API Swagger, disponível em [https://swapi.dev/](https://swapi.dev/).


__Exemplos__
- Exemplo da operação de cadastro de planeta

```json
{
    "nome":"Terra",
    "clima":"temperado",
    "terreno":"rochoso"
}

```

- Exemplo de retorno da busca de planeta
```json
  {
    "id": "6130b344dc7ab21c11489216",
    "name": "Tatooine",
    "climate": "arid",
    "terrain": "desert",
    "numberAppearances": 5
  }

```
