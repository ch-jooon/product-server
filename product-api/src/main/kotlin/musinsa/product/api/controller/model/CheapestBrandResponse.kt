package musinsa.product.api.controller.model

import com.fasterxml.jackson.annotation.JsonProperty
import musinsa.product.core.domain.catalog.LowestPriceSingleBrandCatalog

data class CheapestBrandResponse(
    @JsonProperty("최저가")
    val cheapestBrand: CheapestBrandModel
) {
    companion object {
        fun of(catalog: LowestPriceSingleBrandCatalog): CheapestBrandResponse {
            return CheapestBrandResponse(
                cheapestBrand = CheapestBrandModel.of(catalog)
            )
        }
    }
}

data class CheapestBrandModel(
    @JsonProperty("브랜드")
    val brand: String,
    @JsonProperty("카테고리")
    val categories: List<ProductResponse>,
    @JsonProperty("총액")
    val totalPrice: String,
) {
    companion object {
        fun of(catalog: LowestPriceSingleBrandCatalog): CheapestBrandModel {
            return CheapestBrandModel(
                brand = catalog.brand?.name ?: "",
                categories = catalog.lowestPrice
                    .sortedBy { it.category.id }
                    .map { ProductResponse.withCategory(it) },
                totalPrice = catalog.getTotalPrice().toFormatString()
            )
        }
    }
}
