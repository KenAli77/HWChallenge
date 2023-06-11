package kenali77.projects.hwchallenge.domain.model

import kotlin.math.roundToInt

data class Property(
    val address1: String,
    val address2: String,
    val distance: Distance,
    val district: District,
    val districts: List<DistrictX>,
    val fabSort: FabSort,
    val facilities: List<Facility>,
    val fenceDiscount: Int,
    val freeCancellation: FreeCancellation,
    val freeCancellationAvailable: Boolean,
    val freeCancellationAvailableUntil: String,
    val hbid: Int,
    val hostelworldRecommends: Boolean,
    val hwExtra: Any,
    val id: Int,
    val images: List<Image>,
    val imagesGallery: List<ImagesGallery>,
    val isElevate: Boolean,
    val isFeatured: Boolean,
    val isNew: Boolean,
    val isPromoted: Boolean,
    val latitude: Double,
    val longitude: Double,
    val lowestAverageDormPricePerNight: LowestAverageDormPricePerNight?,
    val lowestAveragePricePerNight: LowestAveragePricePerNight,
    val lowestAveragePrivatePricePerNight: LowestAveragePrivatePricePerNight?,
    val lowestDormPricePerNight: LowestDormPricePerNight?,
    val lowestPricePerNight: LowestPricePerNight,
    val lowestPrivatePricePerNight: LowestPrivatePricePerNight?,
    val name: String,
    val originalLowestAverageDormPricePerNight: OriginalLowestAverageDormPricePerNight?,
    val originalLowestAveragePricePerNight: OriginalLowestAveragePricePerNight?,
    val originalLowestAveragePrivatePricePerNight: OriginalLowestAveragePrivatePricePerNight?,
    val overallRating: OverallRating,
    val overview: String,
    val position: Int,
    val promotions: List<Promotion>,
    val rateRuleViolations: List<RateRuleViolation>,
    val ratingBreakdown: RatingBreakdown,
    val starRating: Int,
    val stayRuleViolations: List<StayRuleViolation>,
    val type: String,
    val veryPopular: Boolean
) {
    fun getDormDiscount():String{
        var dormDiscountPercentage = ""

        if (lowestAverageDormPricePerNight != null) {
            val price = lowestAverageDormPricePerNight
            if (price.original != null) {
                if (price.original > price.value) {
                    dormDiscountPercentage = "${
                        (((price.value.toFloat() / price.original.toFloat()) * 100) - 100).roundToInt()
                    }%"

                }
            }

        }

        return dormDiscountPercentage
    }

    fun getPrivateDiscount():String{
        var privateDiscountPercentage = ""
        if (lowestAveragePrivatePricePerNight != null) {
            val price = lowestAveragePrivatePricePerNight
            if (price.original != null) {
                if (price.original > price.value) {
                    privateDiscountPercentage = "${
                        (((price.value.toFloat() / price.original.toFloat()) * 100) - 100).roundToInt()
                    }%"

                }
            }

        }
        return privateDiscountPercentage
    }

}