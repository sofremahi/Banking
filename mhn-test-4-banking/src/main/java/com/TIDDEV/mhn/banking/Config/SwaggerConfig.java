package com.TIDDEV.mhn.banking.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                description = "banking swagger ",
                title = "simple Banking operations",
                version = "version 1.0.0"
        )
)
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi globalApi(){
        return GroupedOpenApi.builder()
                .group("global.api.banking")
                .pathsToMatch("/**")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    operation.responses(
                            new ApiResponses()
                                    .addApiResponse("400", new ApiResponse().description("Bad Request"))
                                    .addApiResponse("401", new ApiResponse().description("Unauthorized"))
                                    .addApiResponse("403", new ApiResponse().description("Forbidden"))
                                    .addApiResponse("500", new ApiResponse().description("Internal Server Error"))
                                    .addApiResponse("404",new ApiResponse().description("Not Found"))
                    );
                    return operation;
                }).build();
    }

}
