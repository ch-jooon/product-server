package musinsa.product.core.domain.catalog

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import musinsa.product.core.domain.Product
import musinsa.product.core.domain.exception.ProductException
import org.junit.jupiter.api.Test

class CatalogManagerTest {

    private lateinit var sut: CatalogManager

    @Test
    fun `CatalogFinder를 못찾을 경우 NotFoundCatalog 예외가 발생한다`() {
        sut = CatalogManager(
            finders = emptyList()
        )
        val query = CatalogQuery(CatalogType.NONE)

        shouldThrow<ProductException.NotFoundCatalog> {
            sut.findCatalog(query)
        }
    }

    @Test
    fun `지원되는 CatalogFinder 에서 카탈로그를 찾을 수 있다`() {
        sut = CatalogManager(
            finders = listOf(TestNoneCatalogFinder())
        )
        val query = CatalogQuery(CatalogType.NONE)

        val actual = sut.findCatalog(query)

        actual.type shouldBe CatalogType.NONE
    }

    data class TestNoneCatalogFinder(
        var source: List<Product> = emptyList(),
        override val supportType: CatalogType = CatalogType.NONE,
    ) : CatalogFinder<Catalog.Default> {
        override fun findCatalog(query: CatalogQuery): Catalog.Default {
            return Catalog.Default(supportType, source)
        }
    }
}