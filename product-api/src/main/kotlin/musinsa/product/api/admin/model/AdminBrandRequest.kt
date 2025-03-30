package musinsa.product.api.admin.model

import jakarta.validation.constraints.NotBlank

data class AdminBrandCreateRequest(
    @field:NotBlank(message = "브랜드 이름은 필수입니다.")
    val name: String
)

data class AdminBrandUpdateRequest(
    val name: String? = null
)