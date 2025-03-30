package musinsa.product.core.domain

interface CategoryRepository {
    fun save(category: Category): Category
    fun findById(id: Long): Category
    fun findByName(name: String): Category
    fun findAll(): List<Category>
}