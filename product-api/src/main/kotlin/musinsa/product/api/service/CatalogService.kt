package musinsa.product.api.service

import musinsa.product.core.common.cache.CacheService
import musinsa.product.core.domain.catalog.Catalog
import musinsa.product.core.domain.catalog.CatalogManager
import musinsa.product.core.domain.catalog.CatalogQuery
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CatalogService(
    private val catalogManager: CatalogManager,
    private val cacheService: CacheService,
) {

    @Transactional(readOnly = true)
    fun getCatalog(query: CatalogQuery): Catalog {
        val cacheOption = query.getCacheOption()
        return cacheService.getOrPut(cacheOption) {
            catalogManager.findCatalog(query)
        }
    }
}