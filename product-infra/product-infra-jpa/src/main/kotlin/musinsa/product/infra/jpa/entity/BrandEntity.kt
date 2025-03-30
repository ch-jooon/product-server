package musinsa.product.infra.jpa.entity

import jakarta.persistence.*
import musinsa.product.core.domain.Brand
import org.springframework.data.annotation.CreatedDate
import java.time.OffsetDateTime

@Entity
@Table(name = "brand")
data class BrandEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long,

    @Column(name = "name", nullable = false)
    val name: String,

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    val createdAt: OffsetDateTime = OffsetDateTime.now(),

    @Column(name = "deleted_at")
    val deletedAt: OffsetDateTime? = null
) {
    companion object {
        fun from(brand: Brand): BrandEntity {
            return BrandEntity(
                id = brand.id,
                name = brand.name,
                createdAt = brand.createdAt,
                deletedAt = brand.deletedAt
            )
        }
    }

    fun toDomain(): Brand {
        return Brand(
            id = id,
            name = name,
            createdAt = createdAt,
            deletedAt = deletedAt
        )
    }
}