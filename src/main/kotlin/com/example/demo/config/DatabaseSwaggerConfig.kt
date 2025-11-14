package com.example.demo.config

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseSwaggerConfig {

    // 🔹 Aba principal da API (Produtos e Categorias)
    @Bean
    fun apiGroup(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("API Principal (Produtos e Categorias)")
            .packagesToScan("com.example.demo.controller")
            .build()
    }

    // 🔹 Segunda aba apenas para exibir o script SQL
    @Bean
    fun databaseGroup(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("Banco de Dados (Script SQL)")
            .addOpenApiCustomizer { openApi ->
                openApi.info.title("Script SQL — Estrutura do Banco de Dados")
                openApi.info.description(
                    """
                    **Criação e Relacionamento das Tabelas**

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
                    ```

                    🔗 **Relacionamento:**
                    - Uma categoria pode conter vários produtos  
                    - Cada produto pertence a uma única categoria  
                    - Relação **1:N (um para muitos)**

                    Desenvolvido por **Gabriel Silva**
                    """.trimIndent()
                )
            }
            .build()
    }
}



