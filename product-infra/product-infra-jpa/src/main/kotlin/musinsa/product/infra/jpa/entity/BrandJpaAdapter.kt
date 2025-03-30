package musinsa.product.infra.jpa.entity

import musinsa.product.core.domain.Brand
import musinsa.product.core.domain.BrandRepository
import musinsa.product.core.domain.exception.BrandException
import org.springframework.stereotype.Component

@Component
class BrandJpaAdapter(
    private val jpaRepository: BrandJpaRepository,
) : BrandRepository {

    override fun save(brand: Brand): Brand {
        val entity = BrandEntity.from(brand)
        return jpaRepository.save(entity)
            .toDomain()
    }

    override fun findById(id: Long): Brand {
        return jpaRepository.findByIdAndDeletedAtIsNull(id)
            ?.toDomain()
            ?: throw BrandException.NotFound()
    }

    override fun findByName(name: String): Brand {
        return jpaRepository.findByNameAndDeletedAtIsNull(name)
            ?.toDomain()
            ?: throw BrandException.NameNotFound(name)
    }

    override fun findAll(): List<Brand> {
        return jpaRepository.findAllByDeletedAtIsNull()
            .map { it.toDomain() }
    }
}