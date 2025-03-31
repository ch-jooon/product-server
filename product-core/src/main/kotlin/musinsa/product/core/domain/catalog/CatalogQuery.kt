package musinsa.product.core.domain.catalog

import musinsa.product.core.common.cache.CacheName
import musinsa.product.core.common.cache.CacheOption
import musinsa.product.core.domain.catalog.CatalogType.LOWEST_HIGHEST_PRICE_BY_CATEGORY

data class CatalogQuery(
    val type: CatalogType,
    val categoryName: String? = null,
) {
    fun getCacheOption(): CacheOption {
        return CacheOption(
            name = CacheName.CATALOG,
            key = getCacheKey(),
        )
    }

    private fun getCacheKey(): String {
        return "${CacheName.CATALOG.value}:${type.key}${type.getCacheKeySuffix()}"
    }

    private fun CatalogType.getCacheKeySuffix(): String {
        return when (this) {
            LOWEST_HIGHEST_PRICE_BY_CATEGORY -> ":$categoryName"
            else -> ""
        }
    }
}
