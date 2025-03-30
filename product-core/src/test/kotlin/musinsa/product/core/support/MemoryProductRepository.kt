package musinsa.product.core.support

import musinsa.product.core.domain.Product
import musinsa.product.core.domain.ProductRepository
import musinsa.product.core.domain.exception.ProductException

class MemoryProductRepository(
    private val storage: MutableMap<Long, Product> = mutableMapOf(),
) : ProductRepository {

    override fun save(product: Product): Product {
        storage[product.id] = product
        return product
    }

    override fun saveAll(products: List<Product>): List<Product> {
        products.forEach { product ->
            storage[product.id] = product
        }
        return products
    }

    override fun findById(id: Long): Product {
        return storage[id] ?: throw ProductException.NotFound()
    }

    override fun findAllByBrandId(brandId: Long): List<Product> {
        return storage.values.filter { it.brand.id == brandId }
    }

    override fun findMinPriceByCategory(): List<Product> {
        return storage.values
            .groupBy { it.category }
            .mapNotNull { (_, products) ->
                products.sortedByDescending { it.createdAt }
                    .minByOrNull { it.price.value }
            }
    }

    override fun findMinTotalPriceBySingleBrand(): List<Product> {
        return storage.values
            .groupBy { it.brand }
            .minByOrNull { (_, products) -> products.sumOf { it.price.value } }
            ?.value ?: emptyList()
    }

    override fun findMinPriceByCategoryName(categoryName: String): List<Product> {
        return storage.values
            .filter { it.category.name == categoryName }
            .sortedByDescending { it.createdAt }
            .sortedBy { it.price.value }
            .take(1)
    }

    override fun findMaxPriceByCategoryName(categoryName: String): List<Product> {
        return storage.values
            .filter { it.category.name == categoryName }
            .sortedByDescending { it.createdAt }
            .sortedByDescending { it.price.value }
            .take(1)
    }
}