// package com.rfigueroa.figscrm.config;

// import java.time.LocalDate;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Import;
// import org.springframework.http.ResponseEntity;

// import springfox.documentation.builders.PathSelectors;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.oas.annotations.EnableOpenApi;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
// import springfox.documentation.spring.web.plugins.Docket;

// @Configuration
// @Import(SpringDataRestConfiguration.class)
// @EnableOpenApi
// public class SwaggerConfig {
    
//     @Bean
//     public Docket figsCrmApi() {
//         return new Docket(DocumentationType.OAS_30)
//             .groupName("Figs-CRM")
//             .select().apis(RequestHandlerSelectors.any())
//             .paths(PathSelectors.any())
//             .build()
//             .directModelSubstitute(LocalDate.class, String.class)
//             .genericModelSubstitutes(ResponseEntity.class);
//     }
// }
