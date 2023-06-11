package kenali77.projects.hwchallenge.domain.model

import java.math.RoundingMode

data class LowestAveragePrivatePricePerNight(
    val currency: String,
    val original: String?,
    val promotions: Promotions,
    val value: String
) {
    fun getEurValue():String {
        val value = value.toDouble()*0.95

        return value.toBigDecimal().setScale(2, RoundingMode.HALF_UP).toString()
    }
}