package musinsa.product.api.common

data class ErrorResponse(
    val httpCode: Int,
    val message: String? = null,
)