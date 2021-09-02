# Star Wars API

Api Rest Spring Boot para operações com planetas que expoe as funcionalidades:

- Criação manual de um planeta na base de dados local
- Remoção de planeta
- Busca de planeta por ID na base de dados local
- Busca de planeta por nome na base de dados local
- Listagem de todos os planetas cadastrados



__Implantação__
- Implantada (temporariamente) na Google Cloud Plataform, disponível em [http://34.74.31.41:8081](http://34.74.31.41:8081)
- A API se integra com a API pública SWAPI, [https://swapi.dev/about](https://swapi.dev/about) para obter os filmes onde cada planeta tem aparição
- Banco de dados noSQL MongoDB implantado na plataforma em nuvem MongoDB, [https://docs.atlas.mongodb.com](https://docs.atlas.mongodb.com)
- Operações documentadas através da API Swagger2, disponível em [http://34.74.31.41:8081/swagger-ui.html](http://34.74.31.41:8081/swagger-ui.html)



__Exemplos__
- Body do request: cadastro de planeta

```json
{
    "nome":"Terra",
    "clima":"temperado",
    "terreno":"rochoso"
}

```

- Body do response: busca de planeta

```json
  {
    "id": "6130b344dc7ab21c11489216",
    "name": "Tatooine",
    "climate": "arid",
    "terrain": "desert",
    "numberAppearances": 5
  }

```

__Desenvolvedor__

Edmilson Pontes ([edmilsonspontes@gmail.com](edmilsonspontes@gmail.com))
