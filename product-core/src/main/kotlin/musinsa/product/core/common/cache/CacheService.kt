package musinsa.product.core.common.cache


interface CacheService {
    fun <T> get(option: CacheOption): T?
    fun <T> put(option: CacheOption, value: T)

    fun <T> getOrPut(option: CacheOption, block: () -> T): T {
        return get(option) ?: run {
            block().also { put(option, it) }
        }
    }
}