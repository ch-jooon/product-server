package musinsa.product.infra.jpa.entity

import io.kotest.matchers.shouldBe
import musinsa.product.core.domain.Brand
import musinsa.product.infra.jpa.support.EnableDataJpaTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@EnableDataJpaTest
class BrandJpaAdapterTest {

    @Autowired
    private lateinit var jpaRepository: BrandJpaRepository

    private lateinit var sut: BrandJpaAdapter

    @BeforeEach
    fun setUp() {
        sut = BrandJpaAdapter(jpaRepository)
    }

    @Test
    fun `브랜드를 저장할 수 있다`() {
        val brand = Brand(name = "나이키")

        val actual = sut.save(brand)

        actual.name shouldBe "나이키"
        jpaRepository.findById(actual.id).isPresent shouldBe true
    }

    @Test
    fun `브랜드를 수정할 수 있다`() {
        val brand = Brand(name = "아식스")
        val saved = sut.save(brand)

        val changed = saved.changeName("아디다스")
        val actual = sut.save(changed)

        actual.id shouldBe saved.id
        actual.name shouldBe "아디다스"
    }

    @Test
    fun `브랜드 아이디로 조회할 수 있다`() {
        val brand = Brand(name = "호카")
        val saved = sut.save(brand)

        val actual = sut.findById(saved.id)

        actual.id shouldBe saved.id
        actual.name shouldBe "호카"
    }
}