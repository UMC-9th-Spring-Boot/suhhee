package com.example._mission.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//Swagger(OpenAPI) 설정을 위한 클래스
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI missionOpenAPI() {

        // API의 기본 정보를 설정 (제목, 설명, 버전 등)
        Info info = new Info()
                .title("Project")  // 제목
                .description("Project Swagger") //설명
                .version("0.0.1"); // 버전

        String securityScheme = "JWT TOKEN";
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList(securityScheme);
        Components components = new Components()
                .addSecuritySchemes(securityScheme, new SecurityScheme()
                        .name(securityScheme)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("Bearer")
                        .bearerFormat("JWT"));

        // OpenAPI 객체를 생성하여 반환
        return new OpenAPI()
                .addServersItem(new Server().url("/")) // 기본 서버 URL 설정
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}