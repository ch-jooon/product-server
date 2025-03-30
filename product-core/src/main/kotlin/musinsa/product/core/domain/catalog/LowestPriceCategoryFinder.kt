package musinsa.product.core.domain.catalog

import musinsa.product.core.domain.ProductRepository
import org.springframework.stereotype.Component

/**
 * 구현1) 카테고리 별 최저가 상품 카탈로그을 찾는 객체입니다.
 */
@Component
class LowestPriceCategoryFinder(
    private val productRepository: ProductRepository,
) : CatalogFinder<LowestPriceCategoryCatalog> {

    override val supportType: CatalogType = CatalogType.LOWEST_PRICE_BY_CATEGORY

    /**
     * 카테고리 별 최저가 상품 카탈로그을 찾습니다.
     *
     * 최저 가격이 같은 상품이 여러개일 경우, 가장 최근에 등록된 상품을 선택합니다.
     */
    override fun findCatalog(query: CatalogQuery): LowestPriceCategoryCatalog {
        val source = productRepository.findMinPriceByCategory()
        val lowest = source.groupBy { it.category }
            .mapNotNull { (_, products) ->
                products.sortedByDescending { it.createdAt }
                    .minByOrNull { it.price.value }
            }
        return LowestPriceCategoryCatalog(
            lowestPrice = lowest,
        )
    }
}