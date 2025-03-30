package musinsa.product.infra.jpa.entity

import musinsa.product.core.domain.Category
import musinsa.product.core.domain.CategoryRepository
import musinsa.product.core.domain.exception.CategoryException
import org.springframework.stereotype.Component

@Component
class CategoryJpaAdapter(
    private val jpaRepository: CategoryJpaRepository,
) : CategoryRepository {
    override fun save(category: Category): Category {
        val entity = CategoryEntity.from(category)
        return jpaRepository.save(entity)
            .toDomain()
    }

    override fun findById(id: Long): Category {
        return jpaRepository.findById(id)
            .map { it.toDomain() }
            .orElseThrow { CategoryException.NotFound() }
    }

    override fun findByName(name: String): Category {
        return jpaRepository.findAll(offset = 0, limit = 1) {
            select(
                entity(CategoryEntity::class)
            ).from(
                entity(CategoryEntity::class)
            ).whereAnd(
                path(CategoryEntity::name).eq(name)
            )
        }.firstOrNull()?.toDomain()
            ?: throw CategoryException.NameNotFound(name)
    }

    override fun findAll(): List<Category> {
        return jpaRepository.findAllByDeletedAtIsNull()
            .map { it.toDomain() }
    }
}