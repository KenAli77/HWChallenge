package kenali77.projects.hwchallenge.domain.model

data class Pagination(
    val next: String,
    val numberOfPages: Int,
    val prev: String,
    val totalNumberOfItems: Int
)