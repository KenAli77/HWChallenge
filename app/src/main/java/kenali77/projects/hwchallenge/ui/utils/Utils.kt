package kenali77.projects.hwchallenge.ui.utils

import java.math.BigDecimal
import java.math.RoundingMode

fun Int.bigDecimal(): BigDecimal {
    return  (this.toFloat()*0.10f).toBigDecimal().setScale(1, RoundingMode.HALF_UP)
}

