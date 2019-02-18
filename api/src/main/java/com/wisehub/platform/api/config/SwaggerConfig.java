package com.wisehub.platform.api.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wisehub.platform.api"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "WiseHub",
                "WiseHub is a customer intelligence platform that leverages AI-driven data analytics to deliver personalized financial services to best serve customers and drive financial inclusion and economic mobility.",
                "WiseHub Platform Server v1",
                "Terms of service",
                "xxx@xxx.com",
                "License of API",
                "License URL");
        return apiInfo;
    }
}