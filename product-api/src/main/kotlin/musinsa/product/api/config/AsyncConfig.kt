package musinsa.product.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@Configuration
class AsyncConfig {

    @Bean
    fun catalogCacheExecutor(): Executor {
        return Executors.newFixedThreadPool(3)
    }
}