package musinsa.product.core.domain.exception


object ExceptionMessage {
    const val PRODUCT_NOT_FOUND = "상품을 찾을 수 없어요."
    const val PRODUCT_ALREADY_EXISTS = "상품이 이미 존재해요."
    const val BRAND_NOT_FOUND = "브랜드를 찾을 수 없어요."
    const val BRAND_ALREADY_EXISTS_FORMAT = "%s 브랜드가 이미 존재해요."
    const val BRAND_NAME_NOT_FOUND_FORMAT = "%s 브랜드를 찾을 수 없어요."
    const val BRAND_MIN_PRICE_NOT_FOUND = "가장 저렴한 브랜드를 찾을 수 없어요."
    const val CATEGORY_NOT_FOUND = "카테고리를 찾을 수 없어요."
    const val CATEGORY_ALREADY_EXISTS_FORMAT = "%s 카테고리가 이미 존재해요."
    const val CATEGORY_NAME_NOT_FOUND_FORMAT = "%s 카테고리를 찾을 수 없어요."
    const val REQUIRED_PRICE_GREATER_THAN_ZERO = "상품 가격은 0보다 커야 해요."
    const val CATALOG_NOT_FOUND = "카탈로그를 찾을 수 없어요."
}