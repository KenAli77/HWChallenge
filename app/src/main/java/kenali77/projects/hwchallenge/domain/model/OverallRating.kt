package kenali77.projects.hwchallenge.domain.model

import java.math.BigDecimal
import java.math.RoundingMode

data class OverallRating(
    val numberOfRatings: String,
    val overall: Int
) {
    fun getQualitativeScore(): String {
        return when {
            overall >= 90-> {
                "Superb"
            }
            overall >= 80 -> {
                "Fabulous"
            }
            overall >= 70 -> {
                "Very Good"
            }
            overall >= 60 -> {
                "Good"
            }
            else -> {
                ""
            }
        }
    }

    fun getDecimalRating(): BigDecimal {
        return  (overall.toFloat()*0.10f).toBigDecimal().setScale(1, RoundingMode.HALF_UP)
    }
}