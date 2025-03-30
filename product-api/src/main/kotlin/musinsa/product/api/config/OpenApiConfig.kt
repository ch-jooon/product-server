package musinsa.product.api.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("상품 API")
                    .version("v1")
                    .description("상품 API Swagger 문서입니다.")
            ).servers(
                listOf(
                    Server().url("http://localhost:8080")
                        .description("로컬 서버"),
                )
            )
    }
}