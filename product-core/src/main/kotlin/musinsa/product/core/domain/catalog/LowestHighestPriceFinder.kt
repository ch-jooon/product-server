package musinsa.product.core.domain.catalog

import musinsa.product.core.domain.ProductRepository
import musinsa.product.core.domain.exception.CategoryException
import org.springframework.stereotype.Component

/**
 * 구현3) 카테고리 이름으로 최저가, 최고가 상품 카탈로그을 찾는 객체입니다.
 */
@Component
class LowestHighestPriceFinder(
    private val productRepository: ProductRepository,
) : CatalogFinder<LowestHighestPriceCatalog> {

    override val supportType: CatalogType = CatalogType.LOWEST_HIGHEST_PRICE_BY_CATEGORY

    override fun findCatalog(query: CatalogQuery): LowestHighestPriceCatalog {
        val categoryName = query.categoryName
            ?: throw CategoryException.NullName()

        val cheapProducts = productRepository.findMinPriceByCategoryName(categoryName)
        val expensiveProducts = productRepository.findMaxPriceByCategoryName(categoryName)

        return LowestHighestPriceCatalog(
            lowestPrice = cheapProducts,
            highestPrice = expensiveProducts,
        )
    }
}