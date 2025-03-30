package musinsa.product.core.domain.exception

sealed class CategoryException : RuntimeException() {

    class NotFound : CategoryException() {
        override val message: String
            get() = ExceptionMessage.CATEGORY_NOT_FOUND
    }

    class NameNotFound(val name: String) : CategoryException() {
        override val message: String
            get() = ExceptionMessage.CATEGORY_NAME_NOT_FOUND_FORMAT.format(name)
    }

    class NullName : CategoryException() {
        override val message: String
            get() = ExceptionMessage.CATEGORY_NAME_NOT_FOUND_FORMAT.format("null")
    }

    class AlreadyExists(val name: String) : CategoryException() {
        override val message: String
            get() = ExceptionMessage.CATEGORY_ALREADY_EXISTS_FORMAT.format(name)
    }
}