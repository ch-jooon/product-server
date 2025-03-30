package musinsa.product.core.domain.catalog

import musinsa.product.core.domain.ProductRepository
import org.springframework.stereotype.Component

/**
 * 구현2) 최저가 단일 브랜드 상품 카탈로그을 찾는 객체입니다.
 */
@Component
class LowestPriceSingleBrandFinder(
    private val productRepository: ProductRepository,
) : CatalogFinder<LowestPriceSingleBrandCatalog> {

    override val supportType: CatalogType = CatalogType.LOWEST_PRICE_BY_SINGLE_BRAND

    /**
     * 최저가 단일 브랜드 상품 카탈로그을 찾습니다.
     *
     * 같은 카테고리에 속하는 최저 가격 상품이 여러개일 경우, 가장 최근에 등록된 상품을 선택합니다.
     */
    override fun findCatalog(query: CatalogQuery): LowestPriceSingleBrandCatalog {
        val source = productRepository.findMinTotalPriceBySingleBrand()

        val lowestTotalBrandCatalog = source.groupBy { it.category }
            .mapNotNull { (_, products) ->
                products.sortedByDescending { it.createdAt }
                    .minByOrNull { it.price.value }
            }
        return LowestPriceSingleBrandCatalog(
            lowestPrice = lowestTotalBrandCatalog,
        )
    }
}