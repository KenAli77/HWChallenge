package kenali77.projects.hwchallenge.domain.model

data class Facility(
    val name: String,
    val id: String,
    val facilities: List<Facility>
)
