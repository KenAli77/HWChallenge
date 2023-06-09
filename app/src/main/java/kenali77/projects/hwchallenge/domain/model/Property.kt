package kenali77.projects.hwchallenge.domain.model

data class Property(
    val id: Int,
    val isPromoted: Boolean,
    val hbid: Int,
    val name: String,
    val starRating: Int,
    val overallRating: OverallRating,
    val ratingBreakdown: RatingBreakdown,
    val latitude: Double,
    val longitude: Double,
    val isFeatured: Boolean,
    val type: String,
    val address1: String,
    val address2: String,
    val freeCancellationAvailable: Boolean,
    val freeCancellationAvailableUntil: String,
    val district: Any?,
    val districts: List<Any>,
    val freeCancellation: FreeCancellation,
    val lowestPricePerNight: Price,
    val lowestPrivatePricePerNight: Price,
    val lowestDormPricePerNight: Price,
    val lowestAveragePricePerNight: PriceWithPromotions,
    val lowestAverageDormPricePerNight: PriceWithPromotions,
    val lowestAveragePrivatePricePerNight: PriceWithPromotions,
    val isNew: Boolean,
    val overview: String,
    val isElevate: Boolean,
    val hostelworldRecommends: Boolean,
    val distance: Distance,
    val position: Int,
    val hwExtra: Any?,
    val promotions: List<Promotion>,
    val rateRuleViolations: List<Any>,
    val originalLowestAveragePricePerNight: Price,
    val originalLowestAverageDormPricePerNight: Price,
    val originalLowestAveragePrivatePricePerNight: Price,
    val fenceDiscount: Int,
    val veryPopular: Boolean,
    val images: List<Image>,
    val imagesGallery: List<Image>,
    val facilities: List<Facility>
)
