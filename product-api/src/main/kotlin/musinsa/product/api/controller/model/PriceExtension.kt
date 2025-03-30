package musinsa.product.api.controller.model

import musinsa.product.core.domain.Price
import java.text.NumberFormat
import java.util.*


fun Price.toFormatString(locale: Locale = Locale.KOREAN): String {
    return NumberFormat.getNumberInstance(locale)
        .format(this.value)
}