package kenali77.projects.hwchallenge.domain.model

data class Promotion(
    val discount: Int,
    val id: Int,
    val name: String,
    val stack: Boolean,
    val type: String
)