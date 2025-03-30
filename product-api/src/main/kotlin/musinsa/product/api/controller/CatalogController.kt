package musinsa.product.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import musinsa.product.api.common.ApiResponse
import musinsa.product.api.controller.model.CheapestBrandResponse
import musinsa.product.api.controller.model.CheapestCategoryResponse
import musinsa.product.api.controller.model.PriceRangeCategoryResponse
import musinsa.product.api.service.CatalogService
import musinsa.product.core.domain.catalog.CatalogQuery
import musinsa.product.core.domain.catalog.CatalogType
import musinsa.product.core.domain.catalog.LowestHighestPriceCatalog
import musinsa.product.core.domain.catalog.LowestPriceSingleBrandCatalog
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/catalogs", produces = [MediaType.APPLICATION_JSON_VALUE])
@Tag(name = "카탈로그 API", description = "상품을 특정 목적에 맞게 전시하기 위한 조회 기능을 제공하는 API입니다.")
class CatalogController(
    private val catalogService: CatalogService,
) {

    @Operation(
        summary = "카테고리 별 최저가격 카탈로그 조회",
        description = "카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API입니다.",
    )
    @GetMapping("/cheapest-products/categories")
    fun getCheapestByCategory(): ResponseEntity<ApiResponse<CheapestCategoryResponse>> {
        val query = CatalogQuery(CatalogType.LOWEST_PRICE_BY_CATEGORY)

        val catalog = catalogService.getCatalog(query)
        val response = CheapestCategoryResponse.of(catalog)

        return ApiResponse.ok(response)
    }

    @Operation(
        summary = "모든 카테고리 포함 최저가격 브랜드 카탈로그 조회",
        description = "단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API입니다.",
    )
    @GetMapping("/cheapest-products/brand")
    fun getCheapestBySingleBrand(): ResponseEntity<ApiResponse<CheapestBrandResponse>> {
        val query = CatalogQuery(CatalogType.LOWEST_PRICE_BY_SINGLE_BRAND)

        val catalog = catalogService.getCatalog(query) as LowestPriceSingleBrandCatalog
        val response = CheapestBrandResponse.of(catalog)

        return ApiResponse.ok(response)
    }

    @Operation(
        summary = "카테고리 별 최저, 최고 가격 카탈로그 조회",
        description = "카테고리 별 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API입니다.",
    )
    @GetMapping("/cheapest-expensive-products/categories/{categoryName}")
    fun getCheapestExpensiveByCategoryName(
        @PathVariable categoryName: String
    ): ResponseEntity<ApiResponse<PriceRangeCategoryResponse>> {
        if (categoryName.isBlank()) {
            throw IllegalArgumentException("카테고리 이름이 비어있습니다.")
        }
        val query = CatalogQuery(CatalogType.LOWEST_HIGHEST_PRICE_BY_CATEGORY, categoryName.trim())

        val catalog = catalogService.getCatalog(query) as LowestHighestPriceCatalog
        val response = PriceRangeCategoryResponse.of(catalog)

        return ApiResponse.ok(response)
    }
}