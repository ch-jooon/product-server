package musinsa.product.api.admin

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import musinsa.product.api.admin.model.AdminBrandCreateRequest
import musinsa.product.api.admin.model.AdminBrandUpdateRequest
import musinsa.product.api.admin.model.AdminProductCreateRequest
import musinsa.product.api.admin.model.AdminProductUpdateRequest
import musinsa.product.api.common.ApiResponse
import musinsa.product.core.domain.Brand
import musinsa.product.core.domain.Category
import musinsa.product.core.domain.Product
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/admin", produces = [MediaType.APPLICATION_JSON_VALUE])
class AdminController(
    private val adminService: AdminService
) {

    @Tag(name = "관리자 브랜드 API", description = "관리자가 사용하는 브랜드 관리 API입니다.")
    @Operation(summary = "브랜드 전체 조회", description = "브랜드 전체를 조회하는 API입니다.")
    @GetMapping("/brands")
    fun getBrands(): ResponseEntity<ApiResponse<List<Brand>>> {
        val brands = adminService.getBrands()
        return ApiResponse.ok(brands)
    }

    @Tag(name = "관리자 브랜드 API", description = "관리자가 사용하는 브랜드 관리 API입니다.")
    @Operation(summary = "브랜드 생성", description = "브랜드를 생성하는 API입니다.")
    @PostMapping("/brands")
    fun createBrand(
        @Valid @RequestBody request: AdminBrandCreateRequest
    ): ResponseEntity<ApiResponse<Brand>> {
        val brand = adminService.createBrand(request)
        return ApiResponse.ok(brand)
    }

    @Tag(name = "관리자 브랜드 API", description = "관리자가 사용하는 브랜드 관리 API입니다.")
    @Operation(summary = "브랜드 수정", description = "브랜드를 수정하는 API입니다.")
    @PatchMapping("/brands/{id}")
    fun updateBrand(
        @PathVariable id: Long,
        @Valid @RequestBody request: AdminBrandUpdateRequest
    ): ResponseEntity<ApiResponse<Brand>> {
        val brand = adminService.updateBrand(id, request)
        return ApiResponse.ok(brand)
    }

    @Tag(name = "관리자 브랜드 API", description = "관리자가 사용하는 브랜드 관리 API입니다.")
    @Operation(summary = "브랜드 삭제", description = "브랜드를 삭제하는 API입니다.")
    @DeleteMapping("/brands/{id}")
    fun deleteBrand(@PathVariable id: Long): ResponseEntity<ApiResponse<Brand>> {
        val brand = adminService.deleteBrand(id)
        return ApiResponse.ok(brand)
    }

    @Tag(name = "관리자 상품 API", description = "관리자가 사용하는 상품 관리 API입니다.")
    @Operation(summary = "상품 생성", description = "상품을 생성하는 API입니다.")
    @PostMapping("/products")
    fun createProduct(
        @Valid @RequestBody request: AdminProductCreateRequest
    ): ResponseEntity<ApiResponse<Product>> {
        val product = adminService.createProduct(request)
        return ApiResponse.ok(product)
    }

    @Tag(name = "관리자 상품 API", description = "관리자가 사용하는 상품 관리 API입니다.")
    @Operation(summary = "상품 수정", description = "상품을 수정하는 API입니다.")
    @PatchMapping("/products/{id}")
    fun updateProduct(
        @PathVariable id: Long,
        @RequestBody request: AdminProductUpdateRequest
    ): ResponseEntity<ApiResponse<Product>> {
        val product = adminService.updateProduct(id, request)
        return ApiResponse.ok(product)
    }

    @Tag(name = "관리자 상품 API", description = "관리자가 사용하는 상품 관리 API입니다.")
    @Operation(summary = "상품 삭제", description = "상품을 삭제하는 API입니다.")
    @DeleteMapping("/products/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<ApiResponse<Product>> {
        val product = adminService.deleteProduct(id)
        return ApiResponse.ok(product)
    }

    @Tag(name = "관리자 카테고리 API", description = "관리자가 사용하는 카테고리 관리 API입니다.")
    @Operation(summary = "카테고리 전체 조회", description = "카테고리 전체를 조회하는 API입니다.")
    @GetMapping("/categories")
    fun getCategories(): ResponseEntity<ApiResponse<List<Category>>> {
        val categories = adminService.getCategories()
        return ApiResponse.ok(categories)
    }
}