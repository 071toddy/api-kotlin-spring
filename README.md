# API Kotlin Spring Boot — Sistema de Produtos e Categorias

Este projeto foi desenvolvido como parte de um exercício prático para construção de uma **API RESTful** utilizando **Kotlin**, **Spring Boot**, **MySQL** e **Docker**.  
A aplicação realiza operações CRUD (Create, Read, Update, Delete) para **Produtos** e **Categorias**, com integração via **Swagger** e **Postman**.


## **Tecnologias Utilizadas**

-  **Kotlin** — Linguagem principal da aplicação  
-  **Spring Boot** — Framework para criação da API  
-  **MySQL** — Banco de dados relacional  
-  **Docker / Docker Compose** — Containerização da aplicação e banco  
-  **Spring Data JPA** — Integração com o banco  
-  **Swagger UI** — Documentação interativa da API  


##  **Estrutura das Tabelas (Script SQL)**

```sql
CREATE TABLE categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    category_id BIGINT NOT NULL,
    CONSTRAINT fk_product_category
        FOREIGN KEY (category_id)
        REFERENCES categories(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
````

 **Relacionamento:**

* Uma **categoria** pode ter **vários produtos**
* Cada **produto** pertence a uma única categoria
* Relação **1:N (um para muitos)**


##  **Endpoints Principais**

###  **/products**

| Método                  | Descrição                     | Exemplo de uso |
| :---------------------- | :---------------------------- | :------------- |
| `GET /products`         | Lista todos os produtos       | `/products`    |
| `GET /products/{id}`    | Retorna um produto pelo ID    | `/products/1`  |
| `POST /products`        | Cadastra um novo produto      | JSON no corpo  |
| `PUT /products/{id}`    | Atualiza um produto existente | `/products/1`  |
| `DELETE /products/{id}` | Exclui um produto             | `/products/1`  |

 **Exemplo de JSON para POST/PUT:**

```json
{
  "name": "Notebook Gamer",
  "price": 6500.00,
  "category": {
    "id": 1
  }
}
```


###  **/categories**

| Método                    | Descrição                        | Exemplo de uso  |
| :------------------------ | :------------------------------- | :-------------- |
| `GET /categories`         | Lista todas as categorias        | `/categories`   |
| `GET /categories/{id}`    | Retorna uma categoria pelo ID    | `/categories/1` |
| `POST /categories`        | Cadastra uma nova categoria      | JSON no corpo   |
| `PUT /categories/{id}`    | Atualiza uma categoria existente | `/categories/1` |
| `DELETE /categories/{id}` | Exclui uma categoria             | `/categories/1` |

 **Exemplo de JSON para POST/PUT:**

```json
{
  "name": "Computadores"
}
```


##  **Regras de Negócio**

1. **Cálculo de desconto automático**

   * Quando um produto custa mais de **R$ 5.000**, é aplicado um **desconto de 10%** no valor total.
   * Endpoint: `GET /products/discounted`

2. **Listagem de produtos por categoria**

   * Permite visualizar todos os produtos pertencentes a uma categoria específica.
   * Endpoint: `GET /categories/{id}/products`


##  **Acesso ao Swagger**

Após rodar a aplicação, acesse:

 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Lá estarão disponíveis:

* Todos os endpoints da API
* O grupo adicional com o **Script SQL** do banco de dados


## **Executando com Docker Compose**

Se quiser rodar o projeto com Docker:

```bash
docker-compose up
```

O serviço do MySQL e da aplicação Spring Boot serão iniciados automaticamente.


## **Autor**

**Gabriel Silva**
* Estudante de Análise e Desenvolvimento de Sistemas — Faculdade Visconde de Cairu 
* [gabrielsilva.workroom@gmail.com](mailto:gabrielsilva.workroom@gmail.com)
* [GitHub](https://github.com/071toddy)


> Projeto desenvolvido para fins educacionais.
> Tecnologia: Kotlin + Spring Boot + MySQL + Docker + Swagger UI
