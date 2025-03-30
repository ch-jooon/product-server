package musinsa.product.infra.jpa.entity

import jakarta.persistence.*
import musinsa.product.core.domain.Category
import org.springframework.data.annotation.CreatedDate
import java.time.OffsetDateTime

@Entity
@Table(name = "category")
data class CategoryEntity(
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
        fun from(category: Category): CategoryEntity {
            return CategoryEntity(
                id = category.id,
                name = category.name,
                createdAt = category.createdAt,
                deletedAt = category.deletedAt
            )
        }
    }

    fun toDomain(): Category {
        return Category(
            id = id,
            name = name,
            createdAt = createdAt,
            deletedAt = deletedAt
        )
    }
}