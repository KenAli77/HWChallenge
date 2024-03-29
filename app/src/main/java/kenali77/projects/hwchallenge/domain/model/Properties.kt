package kenali77.projects.hwchallenge.domain.model

import kenali77.projects.hwchallenge.domain.search.Searchable

data class Properties(
    val filterData: FilterData,
    val location: Location,
    val locationEn: LocationEn,
    val pagination: Pagination,
    val properties: List<Property>,
    val sortOrder: Any
)  {
}