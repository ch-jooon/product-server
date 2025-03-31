package musinsa.product.api.admin

import musinsa.product.api.admin.model.AdminBrandCreateRequest
import musinsa.product.api.admin.model.AdminBrandUpdateRequest
import musinsa.product.api.admin.model.AdminProductCreateRequest
import musinsa.product.api.admin.model.AdminProductUpdateRequest
import musinsa.product.core.domain.*
import musinsa.product.core.domain.event.BrandEvent
import musinsa.product.core.domain.event.ProductEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 관리자 관련 서비스입니다.
 *
 * 필요 시 별도의 모듈로 분리할 수 있습니다.
 */
@Service
class AdminService(
    private val productRepository: ProductRepository,
    private val brandRepository: BrandRepository,
    private val categoryRepository: CategoryRepository,
    private val eventPublisher: ApplicationEventPublisher,
) {

    @Transactional(readOnly = true)
    fun getBrands(): List<Brand> {
        return brandRepository.findAll()
    }

    @Transactional
    fun createBrand(request: AdminBrandCreateRequest): Brand {
        val brand = Brand(name = request.name)
        return brandRepository.save(brand)
    }

    @Transactional
    fun updateBrand(id: Long, request: AdminBrandUpdateRequest): Brand {
        val brand = brandRepository.findById(id)
        val changed = request.name?.let {
            brand.changeName(it)
        } ?: brand
        return brandRepository.save(changed)
    }

    @Transactional
    fun deleteBrand(id: Long): Brand {
        val deleted = brandRepository.findById(id)
            .run { this.delete() }
            .run { brandRepository.save(this) }

        productRepository.findAllByBrandId(deleted.id)
            .map { it.delete() }
            .also { productRepository.saveAll(it) }

        BrandEvent.Deleted(deleted.id).also {
            eventPublisher.publishEvent(it)
        }
        return deleted
    }

    @Transactional
    fun createProduct(request: AdminProductCreateRequest): Product {
        val brand = brandRepository.findById(request.brandId)
        val category = categoryRepository.findById(request.categoryId)

        val product = Product(
            name = request.name,
            price = Price(request.price.toLong()),
            brand = brand,
            category = category,
        )

        val saved = productRepository.save(product)
        ProductEvent.Created(saved.id).also {
            eventPublisher.publishEvent(it)
        }
        return saved
    }

    @Transactional
    fun updateProduct(id: Long, request: AdminProductUpdateRequest): Product {
        val changed = productRepository.findById(id)
            .run { request.apply(this) }
            .run { productRepository.save(this) }

        ProductEvent.Updated(changed.id).also {
            eventPublisher.publishEvent(it)
        }
        return changed
    }

    @Transactional
    fun deleteProduct(id: Long): Product {
        val deleted = productRepository.findById(id)
            .run { this.delete() }
            .run { productRepository.save(this) }

        ProductEvent.Deleted(deleted.id).also {
            eventPublisher.publishEvent(it)
        }
        return deleted
    }

    @Transactional(readOnly = true)
    fun getCategories(): List<Category> {
        return categoryRepository.findAll()
    }
}