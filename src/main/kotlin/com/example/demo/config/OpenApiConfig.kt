package com.example.demo.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean(name = ["mainOpenAPI"])
    fun mainOpenAPI(): OpenAPI {
        return OpenAPI().info(
            Info()
                .title("API de Gerenciamento de Produtos")
                .version("1.0.0")
                .description(
                    """
                    Esta API foi desenvolvida para fins acadêmicos, 
                    permitindo o gerenciamento de **produtos** e **categorias**, 
                    com operações de **CRUD** e **regras de negócio personalizadas**.

                    🔹 Tecnologias utilizadas:
                    - Kotlin + Spring Boot
                    - MySQL
                    - Swagger UI para documentação
                    - Postman para testes

                    Desenvolvido por **Gabriel Silva**
                    """.trimIndent()
                )
                .contact(
                    Contact()
                        .name("Gabriel Silva")
                        .email("gabrielsilva.workroom@gmail.com")
                        .url("https://github.com/071toddy")
                )
                .license(
                    License()
                        .name("Licença MIT")
                        .url("https://opensource.org/licenses/MIT")
                )
        )
    }
}


