package musinsa.product.infra.cache.config

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.concurrent.TimeUnit

/**
 * 캐시 정보를 설정파일에서 읽어오기 위한 객체입니다.
 */
@ConfigurationProperties(prefix = "cache")
data class CacheProperties(
    val caffeine: CacheCaffeineProperties = CacheCaffeineProperties()
) {
    fun getCaffeineCacheNames(): Array<String> {
        return caffeine.name.toTypedArray()
    }

    fun getCaffeineBuilder(): Caffeine<Any, Any> {
        return Caffeine.newBuilder()
            .maximumSize(caffeine.maxSize)
            .expireAfterWrite(caffeine.ttl.time, caffeine.ttl.unit)
    }

    /**
     * Caffeine 캐시의 상세 spec을 정의합니다.
     *
     * @param name 캐시 이름
     * @param maxSize 캐시 최대 사이즈
     * @param ttl 캐시 만료시간
     */
    data class CacheCaffeineProperties(
        val name: List<String> = listOf(),
        val maxSize: Long = -1,
        val ttl: CacheCaffeineSpecTTLProperties = CacheCaffeineSpecTTLProperties(),
    )

    /**
     * Caffeine 캐시의 TTL을 설정합니다.
     *
     * @param time 캐시의 TTL 시간
     * @param unit 캐시의 TTL 시간 단위
     */
    data class CacheCaffeineSpecTTLProperties(
        val time: Long = 60,
        val unit: TimeUnit = TimeUnit.SECONDS
    )
}

