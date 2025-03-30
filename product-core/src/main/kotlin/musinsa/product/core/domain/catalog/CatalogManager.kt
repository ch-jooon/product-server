package musinsa.product.core.domain.catalog

import musinsa.product.core.domain.exception.ProductException
import org.springframework.stereotype.Component

/**
 * 카탈로그 관련 로직을 처리하는 객체입니다.
 *
 * @property finders: 여러 종류의 카탈로그를 처리하기 위해 CatalogFinder 인터페이스를 구현한 객체들을 주입받아 사용합니다.
 */
@Component
class CatalogManager(
    private val finders: List<CatalogFinder<*>>,
) {
    private val catalogMap: Map<CatalogType, CatalogFinder<*>> = finders.associateBy { it.supportType }

    fun findCatalog(query: CatalogQuery): Catalog {
        val catalogFinder = catalogMap[query.type]
            ?: throw ProductException.NotFoundCatalog()

        return catalogFinder.findCatalog(query)
    }
}