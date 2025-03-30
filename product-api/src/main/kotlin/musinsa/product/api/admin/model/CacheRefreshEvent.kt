package musinsa.product.api.admin.model

import musinsa.product.core.domain.Brand
import musinsa.product.core.domain.Product

data class CacheRefreshEvent(
    val product: Product? = null,
    val brand: Brand? = null,
)
