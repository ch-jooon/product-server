package musinsa.product.core.domain.catalog

import musinsa.product.core.domain.Product

/**
 * 카테고리 별 최저가, 최고가 상품 카탈로그
 */
data class LowestHighestPriceCatalog(
    val lowestPrice: List<Product> = emptyList(),
    val highestPrice: List<Product> = emptyList(),
) : Catalog {
    override val type: CatalogType = CatalogType.LOWEST_HIGHEST_PRICE_BY_CATEGORY
    override val source: List<Product> = lowestPrice + highestPrice
    val category = lowestPrice.firstOrNull()?.category ?: highestPrice.firstOrNull()?.category
}