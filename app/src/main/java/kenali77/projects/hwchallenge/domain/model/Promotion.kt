package kenali77.projects.hwchallenge.domain.model

data class Promotion(
    val id: Int,
    val type: String,
    val stack: Boolean,
    val name: String,
    val discount: Int
)
