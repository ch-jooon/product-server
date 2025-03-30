package musinsa.product.api.controller

import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles


@ActiveProfiles("e2e")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogControllerTest(@LocalServerPort val port: Int) {

    private val baseUrl = "/api/v1/catalogs"

    @Test
    fun `카테고리 별 최저가격 브랜드와 상품 가격, 총액 조회 API`() {
        Given {
            port(port)
            contentType(MediaType.APPLICATION_JSON_VALUE)
        } When {
            get("$baseUrl/cheapest-products/categories")
        } Then {
            log().all()
            statusCode(200)
            contentType(MediaType.APPLICATION_JSON_VALUE)
            body("error", equalTo(null))
            body(
                "data.head", equalTo(listOf("카테고리", "브랜드", "가격")),
                "data.body[0].카테고리", equalTo("상의"),
                "data.body[0].브랜드", not(nullValue()),
                "data.body[0].가격", not(nullValue()),
                "data.body[1].카테고리", equalTo("아우터"),
                "data.body[1].브랜드", not(nullValue()),
                "data.body[1].가격", not(nullValue()),
                "data.body[2].카테고리", equalTo("바지"),
                "data.body[2].브랜드", not(nullValue()),
                "data.body[2].가격", not(nullValue()),
                "data.body[3].카테고리", equalTo("스니커즈"),
                "data.body[3].브랜드", not(nullValue()),
                "data.body[3].가격", not(nullValue()),
                "data.body[4].카테고리", equalTo("가방"),
                "data.body[4].브랜드", not(nullValue()),
                "data.body[4].가격", not(nullValue()),
                "data.body[5].카테고리", equalTo("모자"),
                "data.body[5].브랜드", not(nullValue()),
                "data.body[5].가격", not(nullValue()),
                "data.body[6].카테고리", equalTo("양말"),
                "data.body[6].브랜드", not(nullValue()),
                "data.body[6].가격", not(nullValue()),
                "data.body[7].카테고리", equalTo("액세서리"),
                "data.body[7].브랜드", not(nullValue()),
                "data.body[7].가격", not(nullValue()),
                "data.tail[0].총액", not(nullValue()),
            )
        }
    }

    @Test
    fun `단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액 조회 API`() {
        Given {
            port(port)
            contentType(MediaType.APPLICATION_JSON_VALUE)
        } When {
            get("$baseUrl/cheapest-products/brand")
        } Then {
            log().all()
            statusCode(200)
            contentType(MediaType.APPLICATION_JSON_VALUE)
            body("error", equalTo(null))
            body(
                "data.최저가", not(nullValue()),
                "data.최저가.브랜드", not(nullValue()),
                "data.최저가.카테고리[0].카테고리", equalTo("상의"),
                "data.최저가.카테고리[0].가격", not(nullValue()),
                "data.최저가.카테고리[1].카테고리", equalTo("아우터"),
                "data.최저가.카테고리[1].가격", not(nullValue()),
                "data.최저가.카테고리[2].카테고리", equalTo("바지"),
                "data.최저가.카테고리[2].가격", not(nullValue()),
                "data.최저가.카테고리[3].카테고리", equalTo("스니커즈"),
                "data.최저가.카테고리[3].가격", not(nullValue()),
                "data.최저가.카테고리[4].카테고리", equalTo("가방"),
                "data.최저가.카테고리[4].가격", not(nullValue()),
                "data.최저가.카테고리[5].카테고리", equalTo("모자"),
                "data.최저가.카테고리[5].가격", not(nullValue()),
                "data.최저가.카테고리[6].카테고리", equalTo("양말"),
                "data.최저가.카테고리[6].가격", not(nullValue()),
                "data.최저가.카테고리[7].카테고리", equalTo("액세서리"),
                "data.최저가.카테고리[7].가격", not(nullValue()),
                "data.최저가.총액", not(nullValue()),
            )
        }
    }

    @Test
    fun `카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격 조회 API`() {
        val categoryName = "상의"
        Given {
            port(port)
            contentType(MediaType.APPLICATION_JSON_VALUE)
        } When {
            get("$baseUrl/cheapest-expensive-products/categories/$categoryName")
        } Then {
            log().all()
            statusCode(200)
            contentType(MediaType.APPLICATION_JSON_VALUE)
            body("error", equalTo(null))
            body(
                "data.카테고리", equalTo(categoryName),
                "data.최저가[0].브랜드", not(nullValue()),
                "data.최저가[0].가격", not(nullValue()),
                "data.최고가[0].브랜드", not(nullValue()),
                "data.최고가[0].가격", not(nullValue()),
            )
        }
    }
}