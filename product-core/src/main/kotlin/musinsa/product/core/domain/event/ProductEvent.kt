package musinsa.product.core.domain.event

sealed interface ProductEvent {

    data class Created(
        val productId: Long
    ) : ProductEvent

    data class Updated(
        val productId: Long,
    ) : ProductEvent

    data class Deleted(
        val productId: Long,
    ) : ProductEvent
}