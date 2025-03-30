package musinsa.product.infra.cache.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * application-cache.yml 설정 파일에서 캐시 설정을 읽어서 CacheManager를 빈으로 등록합니다.
 */
@EnableConfigurationProperties(CacheProperties::class)
@EnableCaching
@Configuration(proxyBeanMethods = false)
class CacheConfig(
    private val cacheProperties: CacheProperties,
) {

    @Bean
    @ConditionalOnMissingBean
    fun cacheManager(): CacheManager {
        return CaffeineCacheManager(*cacheProperties.getCaffeineCacheNames()).also {
            it.isAllowNullValues = false
            it.setCaffeine(cacheProperties.getCaffeineBuilder())
        }
    }
}