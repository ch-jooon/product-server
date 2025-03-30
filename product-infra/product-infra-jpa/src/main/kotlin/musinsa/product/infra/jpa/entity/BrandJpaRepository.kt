package musinsa.product.infra.jpa.entity

import org.springframework.data.jpa.repository.JpaRepository

interface BrandJpaRepository : JpaRepository<BrandEntity, Long> {
    fun findByIdAndDeletedAtIsNull(id: Long): BrandEntity?
    fun findByNameAndDeletedAtIsNull(name: String): BrandEntity?
    fun findAllByDeletedAtIsNull(): List<BrandEntity>
}