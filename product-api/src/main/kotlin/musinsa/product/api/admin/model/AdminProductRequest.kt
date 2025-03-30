package musinsa.product.api.admin.model

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import musinsa.product.core.domain.Product

data class AdminProductCreateRequest(
    @field:NotBlank(message = "상품명은 필수입니다.")
    val name: String,
    @field:NotNull(message = "브랜드 ID는 필수입니다.")
    val brandId: Long,
    @field:NotNull(message = "카테고리 ID는 필수입니다.")
    val categoryId: Long,
    @field:Min(value = 0, message = "가격은 0 이상이어야 합니다.")
    val price: Int,
)

data class AdminProductUpdateRequest(
    val name: String? = null,
    val brandId: Long? = null,
    val categoryId: Long? = null,
    val price: Int? = null,
) {
    fun apply(product: Product): Product {
        return product.copy(
            name = name ?: product.name,
            brand = brandId?.let {
                product.brand.copy(id = brandId)
            } ?: product.brand,
            category = categoryId?.let {
                product.category.copy(id = categoryId)
            } ?: product.category,
            price = price?.let {
                product.price.copy(value = it.toLong())
            } ?: product.price,
        )
    }
}