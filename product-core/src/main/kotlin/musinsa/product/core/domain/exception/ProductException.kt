package musinsa.product.core.domain.exception

sealed class ProductException : RuntimeException() {

    class NotFound : ProductException() {
        override val message: String
            get() = ExceptionMessage.PRODUCT_NOT_FOUND
    }

    class AlreadyExists : ProductException() {
        override val message: String
            get() = ExceptionMessage.PRODUCT_ALREADY_EXISTS
    }

    class PriceLessThanZero : ProductException() {
        override val message: String
            get() = ExceptionMessage.REQUIRED_PRICE_GREATER_THAN_ZERO
    }

    class NotFoundCatalog : ProductException() {
        override val message: String
            get() = ExceptionMessage.CATALOG_NOT_FOUND
    }
}


