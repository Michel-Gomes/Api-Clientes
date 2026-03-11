package br.com.cotiinformatica.api_clientes.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    /*
        Método para configurar a documentação do Swagger
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Clientes API - COTI Informática")
                        .version("v1")
                        .description
                                ("API REST para gerenciamento de clientes")
                );
    }
}
