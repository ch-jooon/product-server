package musinsa.product.core.domain.catalog

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import musinsa.product.core.domain.exception.CategoryException
import musinsa.product.core.domain.saveAllFixtures
import musinsa.product.core.fixture.ProductFixture
import musinsa.product.core.support.MemoryProductRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LowestHighestPriceFinderTest {

    private lateinit var repository: MemoryProductRepository
    private lateinit var sut: LowestHighestPriceFinder

    @BeforeEach
    fun setUp() {
        repository = MemoryProductRepository()
        sut = LowestHighestPriceFinder(repository)
    }

    @Test
    fun `카탈로그 조회 쿼리에 카테고리 이름이 없으면 NullName 예외가 발생한다`() {
        val query = CatalogQuery(
            type = CatalogType.LOWEST_HIGHEST_PRICE_BY_CATEGORY
        )

        shouldThrow<CategoryException.NullName> { sut.findCatalog(query) }
    }

    @Test
    fun `카테고리 이름으로 최고가, 최저가 상품 카탈로그를 찾는다`() {
        repository.saveAllFixtures()
        val query = CatalogQuery(
            type = CatalogType.LOWEST_HIGHEST_PRICE_BY_CATEGORY,
            categoryName = ProductFixture.상의.name
        )

        val actual = sut.findCatalog(query)

        actual.size() shouldBe 2
        actual.category shouldBe ProductFixture.상의
        actual.lowestPrice shouldBe listOf(ProductFixture.C_상의)
        actual.highestPrice shouldBe listOf(ProductFixture.I_상의)
        actual.source shouldContainExactly listOf(
            ProductFixture.C_상의,
            ProductFixture.I_상의
        )
    }
}