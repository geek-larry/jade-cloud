// package com.jade.auth.config;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Import;
// import org.springframework.core.annotation.Order;
// import
// springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
// import springfox.documentation.builders.ApiInfoBuilder;
// import springfox.documentation.builders.PathSelectors;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.service.ApiInfo;
// import springfox.documentation.service.Contact;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spring.web.plugins.Docket;
// import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
// @Configuration
// @EnableSwagger2
// @Import(BeanValidatorPluginsConfiguration.class)
// public class SwaggerConfiguration {
//
// @Bean(value = "authApi")
// @Order(value = 1)
// public Docket groupRestApi() {
// return new Docket(DocumentationType.SWAGGER_2)
// .apiInfo(groupApiInfo())
// .select()
// .apis(RequestHandlerSelectors.basePackage("com.jade.auth.controller"))
// .paths(PathSelectors.any())
// .build();
// }
//
// private ApiInfo groupApiInfo() {
// return new ApiInfoBuilder()
// .title("JADE - Restful Apis")
// .description("<div style='font-size:14px;color:red;'>Auth RESTful
// APIs</div>")
// .termsOfServiceUrl("auth-server")
// .contact(new Contact("Larry", "", ""))
// .version("1.0")
// .build();
// }
// }
