package musinsa.product.core.domain

interface ProductRepository {
    fun save(product: Product): Product
    fun saveAll(products: List<Product>): List<Product>
    fun findById(id: Long): Product
    fun findAllByBrandId(brandId: Long): List<Product>

    /**
     * 카테고리 별 최저 가격 상품 목록을 찾습니다.
     */
    fun findMinPriceByCategory(): List<Product>

    /**
     * 카테고리 별 최저 가격 단일 브랜드 상품 목록을 찾습니다.
     */
    fun findMinTotalPriceBySingleBrand(): List<Product>

    /**
     * 카테고리 이름으로 최저 가격 상품 목록을 찾습니다.
     */
    fun findMinPriceByCategoryName(categoryName: String): List<Product>

    /**
     * 카테고리 이름으로 최고 가격 상품 목록을 찾습니다.
     */
    fun findMaxPriceByCategoryName(categoryName: String): List<Product>
}