package musinsa.product.api.controller.model

import com.fasterxml.jackson.annotation.JsonProperty
import musinsa.product.core.domain.catalog.LowestHighestPriceCatalog

data class PriceRangeCategoryResponse(
    @JsonProperty("카테고리")
    val category: String,
    @JsonProperty("최저가")
    val lowestPrice: List<ProductResponse>,
    @JsonProperty("최고가")
    val highestPrice: List<ProductResponse>,
) {
    companion object {
        fun of(catalog: LowestHighestPriceCatalog): PriceRangeCategoryResponse {
            return PriceRangeCategoryResponse(
                category = catalog.category?.name ?: "",
                lowestPrice = catalog.lowestPrice
                    .sortedBy { it.brand.id }
                    .map { ProductResponse.withBrand(it) },
                highestPrice = catalog.highestPrice
                    .sortedBy { it.brand.id }
                    .map { ProductResponse.withBrand(it) },
            )
        }
    }
}