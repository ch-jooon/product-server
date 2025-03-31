package musinsa.product.api.admin

import musinsa.product.core.common.cache.CacheService
import musinsa.product.core.domain.CategoryRepository
import musinsa.product.core.domain.catalog.CatalogManager
import musinsa.product.core.domain.catalog.CatalogQuery
import musinsa.product.core.domain.catalog.CatalogType
import musinsa.product.core.domain.catalog.CatalogType.LOWEST_HIGHEST_PRICE_BY_CATEGORY
import musinsa.product.core.domain.catalog.CatalogType.NONE
import musinsa.product.core.domain.event.BrandEvent
import musinsa.product.core.domain.event.ProductEvent
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener
import java.util.concurrent.Executors

/**
 * 관리자 상품,브랜드 관련 트랜잭션 처리 후 모든 카탈로그를 캐시를 갱신합니다.
 *
 * 별도의 스레드 풀을 사용하여 캐시 갱신을 비동기로 처리합니다.
 * 모든 카탈로그 타입을 조회해서 캐시를 갱신합니다.
 */
@Component
class AdminEventListener(
    private val catalogManager: CatalogManager,
    private val cacheService: CacheService,
    private val categoryRepository: CategoryRepository,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    private val cachePool = Executors.newFixedThreadPool(3)

    @Async("catalogCacheExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun listenProductEvent(event: ProductEvent) {
        logger.info("ProductEvent.${event.javaClass.simpleName} 이벤트 수신: $event")
        refreshCache()
    }

    @Async("catalogCacheExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun listenBrandEvent(event: BrandEvent) {
        logger.info("BrandEvent.${event.javaClass.simpleName} 이벤트 수신: $event")
        refreshCache()
    }

    private fun refreshCache() {
        CatalogType.entries.map { it.toCacheQueries() }
            .flatten()
            .forEach { query ->
                val cacheOption = query.getCacheOption()
                cachePool.submit {
                    runCatching {
                        val catalog = catalogManager.findCatalog(query)
                        cacheService.put(cacheOption, catalog)
                        logger.info("카탈로그 캐시 갱신 성공. key: [${cacheOption.key}]")
                    }.getOrElse {
                        logger.error("카탈로그 캐시 갱신 실패. key: [${cacheOption.key}]", it)
                    }
                }
            }
    }

    fun CatalogType.toCacheQueries(): List<CatalogQuery> {
        return when (this) {
            LOWEST_HIGHEST_PRICE_BY_CATEGORY -> categoryRepository.findAll()
                .map { category -> CatalogQuery(this, category.name) }

            NONE -> emptyList()

            else -> listOf(CatalogQuery(this))
        }
    }
}