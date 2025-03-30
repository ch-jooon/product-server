package musinsa.product.core.domain.catalog

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import musinsa.product.core.domain.Price
import musinsa.product.core.domain.saveAllFixtures
import musinsa.product.core.fixture.ProductFixture
import musinsa.product.core.support.MemoryProductRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LowestPriceSingleBrandFinderTest {

    private lateinit var sut: LowestPriceSingleBrandFinder
    private lateinit var repository: MemoryProductRepository

    @BeforeEach
    fun setUp() {
        repository = MemoryProductRepository()
        sut = LowestPriceSingleBrandFinder(repository)
    }

    @Test
    fun `최저가 단일 브랜드 상품 카탈로그을 찾는다`() {
        repository.saveAllFixtures()
        val query = CatalogQuery(CatalogType.LOWEST_PRICE_BY_SINGLE_BRAND)

        val actual = sut.findCatalog(query)

        actual.groupByBrand().size shouldBe 1
        actual.brand shouldBe ProductFixture.브랜드D
        actual.source shouldContainExactly listOf(
            ProductFixture.D_상의,
            ProductFixture.D_아우터,
            ProductFixture.D_바지,
            ProductFixture.D_스니커즈,
            ProductFixture.D_가방,
            ProductFixture.D_모자,
            ProductFixture.D_양말,
            ProductFixture.D_액세서리,
        )
        actual.getTotalPrice() shouldBe Price(36_100)
    }
}