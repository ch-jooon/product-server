package musinsa.product.infra.jpa.entity

import io.kotest.matchers.collections.shouldContainAnyOf
import io.kotest.matchers.shouldBe
import musinsa.product.core.domain.Brand
import musinsa.product.core.domain.Category
import musinsa.product.core.domain.Price
import musinsa.product.core.domain.Product
import musinsa.product.core.fixture.ProductFixture
import musinsa.product.infra.jpa.support.EnableDataJpaTest
import musinsa.product.infra.jpa.support.resetFixtureId
import musinsa.product.infra.jpa.support.saveAllFixture
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@EnableDataJpaTest
class ProductJpaAdapterTest {

    @Autowired
    private lateinit var jpaRepository: ProductJpaRepository

    @Autowired
    private lateinit var categoryJpaRepository: CategoryJpaRepository

    @Autowired
    private lateinit var em: TestEntityManager

    private lateinit var sut: ProductJpaAdapter

    @BeforeEach
    fun setUp() {
        sut = ProductJpaAdapter(jpaRepository, categoryJpaRepository)
    }

    @AfterEach
    fun tearDown() {
        em.resetFixtureId()
    }

    @Test
    fun `새로운 상품을 저장할 수 있다`() {
        val product = Product(
            name = "상품",
            price = Price(99999),
            category = Category(name = "상의"),
            brand = Brand(name = "나이키"),
        )

        val actual = sut.save(product)

        actual.name shouldBe "상품"
        jpaRepository.findById(actual.id).isPresent shouldBe true
    }

    @Test
    fun `상품 아이디로 조회할 수 있다`() {
        val product = Product(
            name = "상품",
            price = Price(1000),
            category = Category(name = "상의"),
            brand = Brand(name = "나이키"),
        )
        val saved = sut.save(product)

        val actual = sut.findById(saved.id)

        actual.id shouldBe saved.id
        actual.name shouldBe "상품"
    }

    @Test
    fun `카테고리 별 모든 최저가 상품을 조회할 수 있다`() {
        em.saveAllFixture()

        val actual = sut.findMinPriceByCategory()

        actual.size shouldBe 9
        actual.map { it.name } shouldContainAnyOf listOf(
            ProductFixture.C_상의.name,
            ProductFixture.E_아우터.name,
            ProductFixture.D_바지.name,
            ProductFixture.G_스니커즈.name,
            ProductFixture.A_스니커즈.name,
            ProductFixture.A_가방.name,
            ProductFixture.D_모자.name,
            ProductFixture.I_양말.name,
            ProductFixture.F_액세서리.name
        )
    }

    @Test
    fun `전체 금액이 가장 저렴한 단일 브랜드 상품 목록을 조회할 수 있다`() {
        em.saveAllFixture()

        val actual = sut.findMinTotalPriceBySingleBrand()

        actual.size shouldBe ProductFixture.categories.size
        actual.first().brand.name shouldBe ProductFixture.브랜드D.name
        actual.map { it.name } shouldContainAnyOf listOf(
            ProductFixture.D_상의.name,
            ProductFixture.D_아우터.name,
            ProductFixture.D_바지.name,
            ProductFixture.D_스니커즈.name,
            ProductFixture.D_가방.name,
            ProductFixture.D_모자.name,
            ProductFixture.D_양말.name,
            ProductFixture.D_액세서리.name,
        )
    }

    @Test
    fun `카테고리 이름으로 최저 가격 상품을 조회할 수 있다`() {
        em.saveAllFixture()

        val actual = sut.findMinPriceByCategoryName(ProductFixture.상의.name)

        actual.size shouldBe 1
        actual.first().name shouldBe ProductFixture.C_상의.name
    }

    @Test
    fun `카테고리 이름으로 최고 가격 상품을 조회할 수 있다`() {
        em.saveAllFixture()

        val actual = sut.findMaxPriceByCategoryName(ProductFixture.상의.name)

        actual.size shouldBe 1
        actual.first().name shouldBe ProductFixture.I_상의.name
    }
}