package com.tilgungsplan.demo.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerDocConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {

       // registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");


        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket docket()
    {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(generateApiInfo());
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("guru.springframework.controllers"))
                .paths(regex("/product.*"))
                .build()
                .apiInfo(generateApiInfo());
    }
    private ApiInfo generateApiInfo()
    {
        return new ApiInfo("Plan Generator Server Applicant Test Service", "This service is to inform borrowers about the final repayment schedule" +
                "throughout the lifetime of a loan.", "Version 1.0 ",
                "urn:tos", "gnutovamaria85@gmail.com", "", "");
    }
}
