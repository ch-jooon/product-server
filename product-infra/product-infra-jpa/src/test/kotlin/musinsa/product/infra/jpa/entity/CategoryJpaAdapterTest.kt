package musinsa.product.infra.jpa.entity

import io.kotest.matchers.shouldBe
import musinsa.product.core.domain.Category
import musinsa.product.infra.jpa.support.EnableDataJpaTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@EnableDataJpaTest
class CategoryJpaAdapterTest {

    @Autowired
    private lateinit var jpaRepository: CategoryJpaRepository

    private lateinit var sut: CategoryJpaAdapter

    @BeforeEach
    fun setUp() {
        sut = CategoryJpaAdapter(jpaRepository)
    }

    @Test
    fun `카테고리를 저장할 수 있다`() {
        val category = Category(name = "상의")

        val actual = sut.save(category)

        actual.name shouldBe "상의"
        jpaRepository.findById(actual.id).isPresent shouldBe true
    }

    @Test
    fun `카테고리 아이디로 조회할 수 있다`() {
        val category = Category(name = "하의")
        val saved = sut.save(category)

        val actual = sut.findById(saved.id)

        actual.id shouldBe saved.id
        actual.name shouldBe "하의"
    }

    @Test
    fun `카테고리 이름으로 조회할 수 있다`() {
        val category = Category(name = "아우터")
        val saved = sut.save(category)

        val actual = sut.findByName(saved.name)

        actual.id shouldBe saved.id
        actual.name shouldBe "아우터"
    }
}