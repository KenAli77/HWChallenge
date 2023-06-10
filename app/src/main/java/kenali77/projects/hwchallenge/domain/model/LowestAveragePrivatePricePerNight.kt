package kenali77.projects.hwchallenge.domain.model

data class LowestAveragePrivatePricePerNight(
    val currency: String,
    val original: String,
    val promotions: Promotions,
    val value: String
)