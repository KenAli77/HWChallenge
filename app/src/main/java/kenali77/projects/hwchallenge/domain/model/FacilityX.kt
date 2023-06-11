package kenali77.projects.hwchallenge.domain.model

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FreeBreakfast
import androidx.compose.material.icons.rounded.Wifi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kenali77.projects.hwchallenge.ui.theme.LightBlue

data class FacilityX(
    val id: String,
    val name: String
) {

    @Composable
    fun getFacilityIcon(){
        when (id) {
            Facilities.FREEWIFI.name -> {
                Icon(
                    imageVector = Icons.Rounded.Wifi,
                    contentDescription = null,
                    tint = LightBlue,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(1.dp)
                )
            }
            Facilities.BREAKFASTINCLUDED.name -> {
                Icon(
                    imageVector = Icons.Rounded.FreeBreakfast,
                    contentDescription = null,
                    tint = LightBlue,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(1.dp)

                )
            }
            Facilities.BREAKFASTINCLUDED.name -> {
                Icon(
                    imageVector = Icons.Rounded.FreeBreakfast,
                    contentDescription = null,
                    tint = LightBlue,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(1.dp)

                )
            }
        }
    }
}


enum class Facilities{
    FREEWIFI,
    FREEINTERNETACCESS,
    FREECITYTOUR,
    BREAKFASTINCLUDED,
    SANITISATIONBADGE

}

enum class FacilityCategory{
    FACILITYCATEGORYFREE,
    FACILITYCATEGORYSERVICES,
    FACILITYCATEGORYFOODANDDRINK,
    FACILITYCATEGORYENTERTAINMENT,
}

