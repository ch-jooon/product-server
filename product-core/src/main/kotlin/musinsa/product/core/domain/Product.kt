package musinsa.product.core.domain

import java.time.OffsetDateTime


data class Product(
    val id: Long = 0,
    val name: String = "",
    val price: Price = Price.ZERO,
    val brand: Brand,
    val category: Category,
    val createdAt: OffsetDateTime = OffsetDateTime.now(),
    val deletedAt: OffsetDateTime? = null,
) {
    fun delete() = copy(deletedAt = OffsetDateTime.now())
}
