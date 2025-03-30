package musinsa.product.core.domain

import musinsa.product.core.domain.exception.ProductException

data class Price(val value: Long) {
    init {
        if (value < 0) {
            throw ProductException.PriceLessThanZero()
        }
    }

    companion object {
        val ZERO = Price(0)
    }

    operator fun plus(other: Price): Price {
        return Price(this.value + other.value)
    }
}
