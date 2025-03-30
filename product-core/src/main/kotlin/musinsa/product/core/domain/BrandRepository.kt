package musinsa.product.core.domain

interface BrandRepository {
    fun save(brand: Brand): Brand
    fun findById(id: Long): Brand
    fun findByName(name: String): Brand
    fun findAll(): List<Brand>
}