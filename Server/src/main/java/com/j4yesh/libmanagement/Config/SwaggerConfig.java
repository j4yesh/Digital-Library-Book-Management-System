package com.j4yesh.libmanagement.Config;


import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI myCustomConfig(){
        return new OpenAPI()
                .info(new Info()
                        .title("Digital Library Book Management System")
                        .description("Ajackus Java Intern Assignment by Jayesh Badgujar")
                        .version("1.0.0")
                        .license(new License()
                                .name("View My LeetCode Profile")
                                .url("https://leetcode.com/u/j4yesh_/"))

                )
                ;
    }
}
