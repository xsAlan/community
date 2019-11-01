package com.liguo.community.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;

/**
 * Created by dogbro on 2019-11-01 10:56
 */
@Configuration
@EnableSwagger2
public class ApiConfiguration {

    /**
     * 多份不同API，这里可以指定多个bean, 指定不同的basePackage即可
     * @return
     */
    @Bean
    public Docket adminApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .directModelSubstitute(LocalTime.class, String.class)
                .directModelSubstitute(LocalDate.class, String.class)
                .directModelSubstitute(MonthDay.class, String.class)
                .directModelSubstitute(Duration.class, Long.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.liguo.community.controller"))
                .paths(PathSelectors.any())
                .build()
                .groupName("Community API")
                .pathMapping("/")
//                .globalOperationParameters(parameters())
                .apiInfo(apiInfo("Community API", "V1.0", "Community API"));
    }

    private ApiInfo apiInfo(String name, String version, String description) {
        return new ApiInfoBuilder()
                .title(name)
                .version(version)
                .description(description)
                .build();
    }

}
