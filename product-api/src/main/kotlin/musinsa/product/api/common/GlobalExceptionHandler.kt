package musinsa.product.api.common

import musinsa.product.core.domain.exception.BrandException
import musinsa.product.core.domain.exception.CategoryException
import musinsa.product.core.domain.exception.ProductException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.resource.NoResourceFoundException

@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(
        exception = [
            ProductException.NotFound::class,
            ProductException.NotFoundCatalog::class,
            BrandException.NotFound::class,
            BrandException.NameNotFound::class,
            BrandException.MinPriceNotFound::class,
            CategoryException.NotFound::class,
            CategoryException.NameNotFound::class,
        ]
    )
    fun domainNotFoundException(e: RuntimeException): ResponseEntity<ApiResponse<Nothing>> {
        e.logging()
        val res = ErrorResponse(404, e.message)
        return ApiResponse.error(res)
    }

    @ExceptionHandler(
        exception = [
            ProductException.PriceLessThanZero::class,
            ProductException.AlreadyExists::class,
            BrandException.AlreadyExists::class,
            CategoryException.AlreadyExists::class,
        ]
    )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun domainBadRequestException(e: RuntimeException): ResponseEntity<ApiResponse<Nothing>> {
        e.logging()
        val res = ErrorResponse(400, e.message)
        return ApiResponse.error(res)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun noSuchElementException(e: NoSuchElementException): ResponseEntity<ApiResponse<Nothing>> {
        e.logging()
        val res = ErrorResponse(404, e.message)
        return ApiResponse.error(res)
    }

    @ExceptionHandler(IllegalStateException::class)
    fun illegalStateException(e: IllegalStateException): ResponseEntity<ApiResponse<Nothing>> {
        e.logging()
        val res = ErrorResponse(400, e.message)
        return ApiResponse.error(res)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalArgumentException(e: IllegalArgumentException): ResponseEntity<ApiResponse<Nothing>> {
        e.logging()
        val res = ErrorResponse(400, e.message)
        return ApiResponse.error(res)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ApiResponse<Nothing>> {
        val message = e.bindingResult.allErrors.joinToString(", ") { it.defaultMessage ?: "" }
        e.logging()
        val res = ErrorResponse(400, message)
        return ApiResponse.error(res)
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun missingServletRequestParameterException(e: MissingServletRequestParameterException): ResponseEntity<ApiResponse<Nothing>> {
        e.logging()
        val res = ErrorResponse(400, "필수 파라미터가 존재하지 않습니다. [${e.parameterName}]")
        return ApiResponse.error(res)
    }

    @ExceptionHandler(NoResourceFoundException::class)
    fun noResourceFoundException(e: NoResourceFoundException): ResponseEntity<ApiResponse<Nothing>> {
        e.logging()
        val res = ErrorResponse(400, "요청 경로를 찾을 수 없습니다.")
        return ApiResponse.error(res)
    }

    @ExceptionHandler(HttpMessageConversionException::class)
    fun httpMessageNotReadableException(e: HttpMessageConversionException): ResponseEntity<ApiResponse<Nothing>> {
        e.logging()
        val res = ErrorResponse(400, e.message ?: "잘못된 요청입니다.")
        return ApiResponse.error(res)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun exception(e: Exception): ResponseEntity<ApiResponse<Nothing>> {
        e.logging()
        val res = ErrorResponse(500, e.message ?: "알 수 없는 문제가 발생했습니다.")
        return ApiResponse.error(res)
    }

    private fun Exception.logging() = logger.warn("${this.javaClass.simpleName}: ${this.message}")
}