package musinsa.product.infra.jpa.entity

import jakarta.persistence.*
import musinsa.product.core.domain.Brand
import musinsa.product.core.domain.Category
import musinsa.product.core.domain.Price
import musinsa.product.core.domain.Product
import org.springframework.data.annotation.CreatedDate
import java.time.OffsetDateTime

@Entity
@Table(name = "product")
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "price", nullable = false)
    val price: Long,

    @Column(name = "category_id", nullable = false)
    val categoryId: Long,

    @Column(name = "brand_id", nullable = false)
    val brandId: Long,

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    val createdAt: OffsetDateTime,

    @Column(name = "deleted_at")
    val deletedAt: OffsetDateTime? = null
) {
    companion object {
        fun from(product: Product): ProductEntity {
            return ProductEntity(
                id = product.id,
                name = product.name,
                price = product.price.value,
                categoryId = product.category.id,
                brandId = product.brand.id,
                createdAt = product.createdAt,
                deletedAt = product.deletedAt
            )
        }
    }

    fun toDomain(): Product {
        return Product(
            id = id,
            name = name,
            price = Price(price),
            category = Category(id = categoryId),
            brand = Brand(id = brandId),
            createdAt = createdAt,
            deletedAt = deletedAt
        )
    }
}