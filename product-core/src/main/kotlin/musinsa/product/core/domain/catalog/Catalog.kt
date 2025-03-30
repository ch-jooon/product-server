package musinsa.product.core.domain.catalog

import musinsa.product.core.domain.Brand
import musinsa.product.core.domain.Price
import musinsa.product.core.domain.Product

/**
 * Catalog(카탈로그)는 여러 상품(Product)을 특정 목적에 맞게 전시(Display)하기 위한 그룹의 개념입니다.
 */
interface Catalog {
    val type: CatalogType
    val source: List<Product>

    /**
     * 카탈로그에 포함된 상품(Product)의 총 가격을 계산합니다.
     */
    fun getTotalPrice(): Price {
        val totalPrice = source.sumOf { it.price.value }
        return Price(totalPrice)
    }

    /**
     * 카탈로그에 포함된 상품(Product)을 브랜드(Brand)별로 그룹화합니다.
     */
    fun groupByBrand(): Map<Brand, List<Product>> {
        return source.groupBy { it.brand }
    }

    fun size(): Int {
        return source.size
    }

    data class Default(
        override val type: CatalogType = CatalogType.NONE,
        override val source: List<Product> = emptyList(),
    ) : Catalog
}