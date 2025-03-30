package musinsa.product.core.domain.catalog

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import musinsa.product.core.domain.Price
import musinsa.product.core.domain.catalog.CatalogType.LOWEST_PRICE_BY_CATEGORY
import musinsa.product.core.domain.saveAllFixtures
import musinsa.product.core.fixture.ProductFixture
import musinsa.product.core.support.MemoryProductRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LowestPriceCategoryFinderTest {

    private lateinit var repository: MemoryProductRepository
    private lateinit var sut: LowestPriceCategoryFinder

    @BeforeEach
    fun setUp() {
        repository = MemoryProductRepository()
        sut = LowestPriceCategoryFinder(repository)
    }

    @Test
    fun `카테고리 별 최저 가격 상품 카탈로그를 찾는다`() {
        repository.saveAllFixtures()
        val query = CatalogQuery(LOWEST_PRICE_BY_CATEGORY)

        val actual = sut.findCatalog(query)

        println(actual)

        actual.size() shouldBe 8
        actual.source shouldContainExactly listOf(
            ProductFixture.C_상의,
            ProductFixture.E_아우터,
            ProductFixture.D_바지,
            ProductFixture.G_스니커즈,
            ProductFixture.A_가방,
            ProductFixture.D_모자,
            ProductFixture.I_양말,
            ProductFixture.F_액세서리
        )
        actual.getTotalPrice() shouldBe Price(34_100)
    }
}