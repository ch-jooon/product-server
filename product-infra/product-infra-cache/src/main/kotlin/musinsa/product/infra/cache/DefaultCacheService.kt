package musinsa.product.infra.cache

import musinsa.product.core.common.cache.CacheOption
import musinsa.product.core.common.cache.CacheService
import org.springframework.cache.CacheManager
import org.springframework.stereotype.Component

@Component
class DefaultCacheService(
    private val cacheManager: CacheManager
) : CacheService {

    override fun <T> get(option: CacheOption): T? {
        val cache = cacheManager.getCache(option.name.value)
        return cache?.get(option.key)?.get()?.let {
            runCatching {
                it as T?
            }.getOrNull()
        }
    }

    override fun <T> put(option: CacheOption, value: T) {
        val cache = cacheManager.getCache(option.name.value)
        if (value == null) {
            return
        }
        if (value is Collection<*> && value.isEmpty()) {
            return
        }
        cache?.put(option.key, value)
    }
}