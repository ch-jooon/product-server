package musinsa.product.api.common

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.ResponseEntity

data class ApiResponse<T>(
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val data: T? = null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val error: ErrorResponse? = null,
) {
    companion object {
        fun <T : Any> ok(data: T): ResponseEntity<ApiResponse<T>> {
            return ResponseEntity.ok(
                ApiResponse(
                    data = data,
                    error = null
                )
            )
        }

        fun error(error: ErrorResponse): ResponseEntity<ApiResponse<Nothing>> {
            val response = ApiResponse(
                data = null,
                error = error
            )
            return ResponseEntity.status(error.httpCode).body(response)
        }
    }

    fun ok(): ResponseEntity<ApiResponse<T>> {
        return ResponseEntity.ok(this)
    }
}
