package musinsa.product.core.domain.catalog

import musinsa.product.core.domain.Product

/**
 * 최저가 단일 브랜드 상품 카탈로그
 */
data class LowestPriceSingleBrandCatalog(
    val lowestPrice: List<Product> = emptyList(),
) : Catalog {
    override val type: CatalogType = CatalogType.LOWEST_PRICE_BY_SINGLE_BRAND
    override val source: List<Product> = lowestPrice
    val brand = lowestPrice.firstOrNull()?.brand
}
