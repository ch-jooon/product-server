package musinsa.product.infra.jpa.entity

data class BrandCategoryMinPriceProjection(
    val brandId: Long,
    val categoryId: Long,
    val minPrice: Long,
)

fun List<BrandCategoryMinPriceProjection?>.toLowestTotalPriceBrandId(totalCategoryCount: Long): Long? {
    return this.asSequence()
        .filterNotNull()
        .groupBy { it.brandId }
        .filter { it.value.size == totalCategoryCount.toInt() }
        .map { (brandId, products) ->
            val totalMinPrice = products.sumOf { it.minPrice }
            brandId to totalMinPrice
        }
        .minByOrNull { it.second }
        ?.first
}