package musinsa.product.core.domain

import musinsa.product.core.fixture.ProductFixture

fun ProductRepository.saveAllFixtures() {
    ProductFixture.products.forEach { product ->
        runCatching {
            save(product)
        }.getOrElse {
            save(product.copy(id = 0))
        }
    }
}