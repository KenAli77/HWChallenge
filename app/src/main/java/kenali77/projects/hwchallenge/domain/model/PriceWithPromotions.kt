package kenali77.projects.hwchallenge.domain.model

data class PriceWithPromotions (
        val value: String,
        val currency: String,
        val promotions: Promotion?,
        val original: String

)
