package musinsa.product.core.domain.exception

sealed class BrandException : RuntimeException() {

    class NotFound(
        override val message: String = ExceptionMessage.BRAND_NOT_FOUND,
    ) : BrandException()

    class NameNotFound(private val name: String) : BrandException() {
        override val message: String
            get() = ExceptionMessage.BRAND_NAME_NOT_FOUND_FORMAT.format(name)
    }

    class MinPriceNotFound(
        override val message: String = ExceptionMessage.BRAND_MIN_PRICE_NOT_FOUND,
    ) : BrandException()

    class AlreadyExists(private val name: String) : BrandException() {
        override val message: String
            get() = ExceptionMessage.BRAND_ALREADY_EXISTS_FORMAT.format(name)
    }
}