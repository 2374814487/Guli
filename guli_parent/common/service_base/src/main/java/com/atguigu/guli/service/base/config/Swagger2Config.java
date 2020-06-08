package com.atguigu.guli.service.base.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket webApiConfig(){
       return new Docket(DocumentationType.SWAGGER_2)
               .groupName("webApi")
               .apiInfo(this.webApiInfo())
               .select()
               .paths(Predicates.and(PathSelectors.regex("/api/.*")))
               .build();
    }

    @Bean
    public Docket adminApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(this.adminApiInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build();
    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                    .title("网站的API文档")
                    .description("本文档描述了谷粒学院的API接口定义")
                    .version("1.0")
                    .contact(new Contact("陈林腾","http://www.baidu.com","2374814487@qq.com"))
                    .build();
    }

    private ApiInfo adminApiInfo(){
        return new ApiInfoBuilder()
                .title("后台管理员的API文档")
                .description("本文档描述了谷粒学院的管理员接口定义")
                .version("1.0")
                .contact(new Contact("陈林腾","http://www.baidu.com","2374814487@qq.com"))
                .build();
    }

}
