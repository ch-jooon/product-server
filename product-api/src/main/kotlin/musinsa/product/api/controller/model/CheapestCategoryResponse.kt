package musinsa.product.api.controller.model

import musinsa.product.core.domain.catalog.Catalog

data class CheapestCategoryResponse(
    val head: List<String> = emptyList(),
    val body: List<ProductResponse> = emptyList(),
    val tail: List<Map<String, String>> = emptyList(),
) {
    companion object {
        fun of(catalog: Catalog): CheapestCategoryResponse {
            return CheapestCategoryResponse(
                head = listOf("카테고리", "브랜드", "가격"),
                body = catalog.source.sortedBy { it.category.id }
                    .map { ProductResponse.of(it) },
                tail = listOf(
                    mapOf(
                        "총액" to catalog.getTotalPrice().toFormatString(),
                    )
                )
            )
        }
    }
}
