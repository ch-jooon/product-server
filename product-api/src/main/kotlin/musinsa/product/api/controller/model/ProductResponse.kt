package musinsa.product.api.controller.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import musinsa.product.core.domain.Product

data class ProductResponse(
    @JsonProperty("카테고리")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val category: String? = null,
    @JsonProperty("브랜드")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val brand: String? = null,
    @JsonProperty("가격")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val price: String? = null,
) {
    companion object {
        fun of(product: Product): ProductResponse {
            return ProductResponse(
                category = product.category.name,
                brand = product.brand.name,
                price = product.price.toFormatString(),
            )
        }

        fun withCategory(product: Product): ProductResponse {
            return ProductResponse(
                category = product.category.name,
                brand = null,
                price = product.price.toFormatString(),
            )
        }

        fun withBrand(product: Product): ProductResponse {
            return ProductResponse(
                category = null,
                brand = product.brand.name,
                price = product.price.toFormatString(),
            )
        }
    }
}
