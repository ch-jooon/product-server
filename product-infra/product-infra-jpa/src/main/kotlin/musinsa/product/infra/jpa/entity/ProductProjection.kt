package musinsa.product.infra.jpa.entity

import musinsa.product.core.domain.Brand
import musinsa.product.core.domain.Category
import musinsa.product.core.domain.Price
import musinsa.product.core.domain.Product
import java.time.OffsetDateTime

data class ProductProjection(
    val id: Long,
    val name: String,
    val price: Long,
    val createdAt: OffsetDateTime,
    val brandId: Long,
    val brandName: String?,
    val brandCreatedAt: OffsetDateTime?,
    val categoryId: Long,
    val categoryName: String?,
    val categoryCreatedAt: OffsetDateTime?,
) {

    fun toDomain(): Product {
        return Product(
            id = id,
            name = name,
            price = Price(price),
            createdAt = createdAt,
            brand = Brand(
                id = brandId,
                name = brandName ?: "",
                createdAt = brandCreatedAt ?: createdAt
            ),
            category = Category(
                id = categoryId,
                name = categoryName ?: "",
                createdAt = categoryCreatedAt ?: createdAt
            )
        )
    }
}