package musinsa.product.core.domain

import java.time.OffsetDateTime

data class Brand(
    val id: Long = 0,
    val name: String = "",
    val createdAt: OffsetDateTime = OffsetDateTime.now(),
    val deletedAt: OffsetDateTime? = null,
) {
    fun changeName(name: String): Brand = copy(name = name)

    fun delete(): Brand = copy(deletedAt = OffsetDateTime.now())
}
