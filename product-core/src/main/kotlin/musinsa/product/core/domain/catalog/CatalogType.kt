package musinsa.product.core.domain.catalog

enum class CatalogType(val title: String, val key: String) {
    LOWEST_PRICE_BY_CATEGORY(
        title = "카테고리 별 최저가 상품",
        key = "lowest-price-category",
    ),
    LOWEST_PRICE_BY_SINGLE_BRAND(
        title = "최저가 단일 브랜드 상품",
        key = "lowest-price-single-brand",
    ),
    LOWEST_HIGHEST_PRICE_BY_CATEGORY(
        title = "카테고리 별 최저가 및 최고가 상품",
        key = "lowest-highest-price-category",
    ),
    NONE("", "");
}