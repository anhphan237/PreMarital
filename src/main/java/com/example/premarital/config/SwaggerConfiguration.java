package com.example.premarital.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfiguration {



    @Bean
    public OpenAPI provideCustomOpenAPI() {

        List<Tag> orderedTags = Arrays.asList(
                new Tag().name("Authentication").description("Authentication Endpoints"),
                new Tag().name("Quiz Service").description("Quiz for user Endpoints"),
                new Tag().name("Quiz Configurator").description("Quiz Configurator for Therapist Endpoints")
        );

        return new OpenAPI()
                .info(new Info()
                        .title("API for Premarital Platform")
                        .version("1.0")
                        .description("Premarital Platform"))
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("BearerAuth",
                                new SecurityScheme()
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .tags(orderedTags);
    }
}
