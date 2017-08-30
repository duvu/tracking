package com.vd5.tracking.conf;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author beou on 8/30/17 23:09
 * @version 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public WebMvcConfigurerAdapter forwardToIndex() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/api-docs").setViewName(
                        "forward:/swagger-ui.html");
            }
        };
    }

    @Bean
    public Docket restApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.vd5.tracking"))
                .paths(apiPaths())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("VD5 Tracking APIs", "http://vd5track.com", "developer@vd5track.com");
        return new ApiInfoBuilder()
                .title("VD5 Tracking API Documents")
                .description("Documents with Swagger 2")
                .termsOfServiceUrl("http://vd5track.com")
                .contact(contact)
                .license("vd5track.com")
                .licenseUrl("http://vd5track.com")
                .version("1.0")
                .build();
    }

    private Predicate<String> apiPaths() {
        return Predicates.or(
                regex("/manage.*"),
                regex("/api.*"),
                regex("/api-docs.*"));
    }
}
