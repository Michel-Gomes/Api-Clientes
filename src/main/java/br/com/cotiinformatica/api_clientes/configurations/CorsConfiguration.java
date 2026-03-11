package br.com.cotiinformatica.api_clientes.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")//libera para todos os endpoints da API
                .allowedOrigins("http://localhost:4200")//url do Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE") //métodos permitidos
                .allowedHeaders("*") // libera todos os headers
                .allowCredentials(true); // se precisar enviar cookies/autenticação

    }
}
