package musinsa.product.api.controller.model

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.matchers.shouldBe
import musinsa.product.core.domain.Brand
import musinsa.product.core.domain.Category
import musinsa.product.core.domain.Price
import musinsa.product.core.domain.Product
import org.junit.jupiter.api.Test

class ProductResponseTest {

    @Test
    fun `상품 객체 변환 테스트`() {
        val product = Product(
            category = Category(name = "상의"),
            brand = Brand(name = "무신사 스탠다드"),
            price = Price(10000)
        )
        val sut = ProductResponse.of(product)

        ObjectMapper().writeValueAsString(sut).also { println(it) }

        sut.category shouldBe "상의"
        sut.brand shouldBe "무신사 스탠다드"
        sut.price shouldBe "10,000"
    }
}