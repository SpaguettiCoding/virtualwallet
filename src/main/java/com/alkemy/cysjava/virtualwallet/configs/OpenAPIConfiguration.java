package com.alkemy.cysjava.virtualwallet.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI customOpenAPIHead(){
        return new OpenAPI().info(new Info()
                .title("VIRTUAL WALLET API - spaghettiCoding")
                .version("1.0")
                .description("Virtual Wallet API developed by spaghettiCoding")
                .termsOfService("http://swagger.io/terms/")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"))
        );
    }
}
