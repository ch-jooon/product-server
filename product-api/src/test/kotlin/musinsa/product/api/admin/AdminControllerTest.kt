package musinsa.product.api.admin

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import musinsa.product.core.domain.ProductRepository
import musinsa.product.core.domain.exception.ProductException
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("e2e")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdminControllerTest(@LocalServerPort val port: Int) {

    @Autowired
    private lateinit var productRepository: ProductRepository

    val baseUrl = "/api/v1/admin"

    @Test
    fun `브랜드 전체 조회 API`() {
        Given {
            port(port)
            contentType(MediaType.APPLICATION_JSON_VALUE)
        } When {
            get("$baseUrl/brands")
        } Then {
            log().all()
            statusCode(200)
            contentType(MediaType.APPLICATION_JSON_VALUE)
            body("error", equalTo(null))
            body(
                "data", any(List::class.java),
                "data[0].id", notNullValue(),
                "data[0].name", notNullValue(),
            )
        }
    }

    @Test
    fun `브랜드 생성 API`() {
        Given {
            port(port)
            contentType(MediaType.APPLICATION_JSON_VALUE)
            body(
                """
                {
                    "name": "testBrand"
                }
                """.trimIndent()
            )
        } When {
            post("$baseUrl/brands")
        } Then {
            log().all()
            statusCode(200)
            contentType(MediaType.APPLICATION_JSON_VALUE)
            body("error", equalTo(null))
            body(
                "data.id", notNullValue(),
                "data.name", equalTo("testBrand"),
            )
        }
    }

    @Test
    fun `브랜드 수정 API`() {
        Given {
            port(port)
            contentType(MediaType.APPLICATION_JSON_VALUE)
            body(
                """
                {
                    "name": "updatedBrand"
                }
                """.trimIndent()
            )
        } When {
            patch("$baseUrl/brands/1")
        } Then {
            log().all()
            statusCode(200)
            contentType(MediaType.APPLICATION_JSON_VALUE)
            body("error", equalTo(null))
            body(
                "data.id", equalTo(1),
                "data.name", equalTo("updatedBrand"),
            )
        }
    }

    @Test
    fun `브랜드 삭제 API`() {
        Given {
            port(port)
            contentType(MediaType.APPLICATION_JSON_VALUE)
        } When {
            delete("$baseUrl/brands/1")
        } Then {
            log().all()
            statusCode(200)
            contentType(MediaType.APPLICATION_JSON_VALUE)
            body("error", equalTo(null))
            body(
                "data.id", equalTo(1),
                "data.name", equalTo("updatedBrand"),
            )
        }
    }

    @Test
    fun `상품 생성 API`() {
        Given {
            port(port)
            contentType(MediaType.APPLICATION_JSON_VALUE)
            body(
                """
                {
                    "name": "테스트용 상품",
                    "brandId": 1,
                    "categoryId": 1,
                    "price": 10000
                }
                """.trimIndent()
            )
        } When {
            post("$baseUrl/products")
        } Then {
            log().all()
            statusCode(200)
            contentType(MediaType.APPLICATION_JSON_VALUE)
            body("error", equalTo(null))
            body(
                "data.id", notNullValue(),
                "data.name", equalTo("테스트용 상품"),
                "data.brand.id", equalTo(1),
                "data.price.value", equalTo(10000),
                "data.category.id", equalTo(1),
            )
        }
    }

    @Test
    fun `상품 수정 API`() {
        val productId = 1
        val changeName = "수정"
        val before = productRepository.findById(productId.toLong())
        before.name shouldNotBe changeName

        Given {
            port(port)
            contentType(MediaType.APPLICATION_JSON_VALUE)
            body(
                """
                {
                    "name": "$changeName"
                }
                """.trimIndent()
            )
        } When {
            patch("$baseUrl/products/$productId")
        } Then {
            log().all()
            statusCode(200)
            contentType(MediaType.APPLICATION_JSON_VALUE)
            body("error", equalTo(null))
            body(
                "data.id", equalTo(productId),
                "data.name", equalTo(changeName),
            )
        }
        val actual = productRepository.findById(productId.toLong())

        actual.name shouldBe changeName
    }

    @Test
    fun `상품 삭제 API`() {
        val productId = 1
        val before = productRepository.findById(productId.toLong())
        before.deletedAt shouldBe null

        Given {
            port(port)
            contentType(MediaType.APPLICATION_JSON_VALUE)
        } When {
            delete("$baseUrl/products/$productId")
        } Then {
            log().all()
            statusCode(200)
            contentType(MediaType.APPLICATION_JSON_VALUE)
            body("error", equalTo(null))
            body(
                "data.id", equalTo(productId),
            )
        }

        shouldThrow<ProductException.NotFound> {
            productRepository.findById(productId.toLong())
        }
    }

    @Test
    fun `카테고리 전체 조회 API`() {
        Given {
            port(port)
            contentType(MediaType.APPLICATION_JSON_VALUE)
        } When {
            get("$baseUrl/categories")
        } Then {
            log().all()
            statusCode(200)
            contentType(MediaType.APPLICATION_JSON_VALUE)
            body("error", equalTo(null))
            body(
                "data", any(List::class.java),
                "data[0].id", notNullValue(),
                "data[0].name", notNullValue(),
            )
        }
    }
}