package musinsa.product.core.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import musinsa.product.core.domain.exception.ProductException
import org.junit.jupiter.api.Test

class PriceTest {

    @Test
    fun `0이상 값으로 가격을 생성할 수 있다`() {
        val value = 0L
        Price(value).value shouldBe value

        val value2 = 1000L
        Price(value2).value shouldBe value2
    }

    @Test
    fun `0보다 작은 값으로 가격 생성 시 PriceLessThanZero 예외가 발생한다`() {
        val value = -1L

        shouldThrow<ProductException.PriceLessThanZero> {
            Price(value)
        }
    }
}