package kenali77.projects.hwchallenge.domain.model

data class FacilityX(
    val id: String,
    val name: String
) {

}

enum class Facilities{
    FREEWIFI,
    FREEINTERNETACCESS,
    FREECITYTOUR,
    BREAKFASTINCLUDED

}

enum class FacilityCategory{
    FACILITYCATEGORYFREE,
    FACILITYCATEGORYSERVICES,
    FACILITYCATEGORYFOODANDDRINK,
    FACILITYCATEGORYENTERTAINMENT,
}

