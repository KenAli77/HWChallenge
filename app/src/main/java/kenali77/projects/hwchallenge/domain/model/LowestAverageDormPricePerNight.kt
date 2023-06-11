package kenali77.projects.hwchallenge.domain.model

import java.math.RoundingMode

data class LowestAverageDormPricePerNight(
    val currency: String,
    val original: String?,
    val promotions: Promotions?,
    val value: String
) {
   fun getEurValue():String {
       val value = value.toDouble()*0.95

       return value.toBigDecimal().setScale(2, RoundingMode.HALF_UP).toString()
   }

   fun isDiscounted():Boolean {
       original?.let {
           return original > getEurValue()
       }
       return false
   }


}