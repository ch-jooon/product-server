package musinsa.product.core.domain.catalog

import musinsa.product.core.domain.Product

/**
 * 카테고리 별 최저가 상품 카탈로그
 */
data class LowestPriceCategoryCatalog(
    val lowestPrice: List<Product> = emptyList(),
) : Catalog {
    override val type: CatalogType = CatalogType.LOWEST_PRICE_BY_CATEGORY
    override val source: List<Product> = lowestPrice
}