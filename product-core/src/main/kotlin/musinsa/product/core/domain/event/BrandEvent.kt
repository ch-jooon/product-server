package musinsa.product.core.domain.event

sealed interface BrandEvent {

    data class Deleted(
        val brandId: Long,
    ) : BrandEvent
}