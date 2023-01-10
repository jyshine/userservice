package com.june.sample.userservice.core.config;

import java.util.Collections;
import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ConditionalOnExpression("${web.swagger.enabled}")
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * default group
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()))
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("회원 관리 API")
                .version("1.0")
                .description("OAuth2 Authorize 방법 :" +
                        "<ul>" +
                        "<li>username은 전화번호를 입력해주세요. </li>" +
                        "<li><code>$ curl -u front-ui:front-ui! http://localhost:8080/oauth/token -F grant_type=password -F username=XXXX -F password=YYYY</code></li>" +
                        "<li>access_token 값을 복사</li>" +
                        "<li>[Authorize] 버튼 누른 뒤 apiKey value 값에 \"Bearer {access_token}\" 입력 </li>" +
                        "</ul> ")
                .build();
    }

    private ApiKey apiKey() {
        //`apiKey` is the name of the APIKey, `Authorization` is the key in the request header
        return new ApiKey("apiKey", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        // 모든 /api/** 에 대해서 Authorize 적용.
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.ant("/api/**"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(
                new SecurityReference("apiKey", authorizationScopes));
    }

/*
참조: https://springfox.github.io/springfox/docs/current/#getting-started-spring-boot
    https://springfox.github.io/springfox/docs/current/#configuring-springfox
 */
}