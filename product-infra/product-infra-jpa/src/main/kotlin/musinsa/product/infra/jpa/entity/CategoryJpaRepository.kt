package musinsa.product.infra.jpa.entity

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryJpaRepository : JpaRepository<CategoryEntity, Long>, KotlinJdslJpqlExecutor {
    fun findByName(name: String): CategoryEntity?
    fun findAllByDeletedAtIsNull(): List<CategoryEntity>
}