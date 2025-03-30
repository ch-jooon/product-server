package musinsa.product.infra.jpa.entity

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.springframework.data.jpa.repository.JpaRepository

interface ProductJpaRepository : JpaRepository<ProductEntity, Long>, KotlinJdslJpqlExecutor {
    fun findAllByBrandId(brandId: Long): List<ProductEntity>
}