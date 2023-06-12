package kenali77.projects.hwchallenge.domain.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FreeBreakfast
import androidx.compose.material.icons.rounded.Wifi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kenali77.projects.hwchallenge.R
import kenali77.projects.hwchallenge.ui.theme.LightBlue

data class FacilityX(
    val id: String,
    val name: String
) {

    @Composable
    fun getFacilityIcon() {
        when (id) {
            Facilities.FREEWIFI.name -> {
                Icon(
                    imageVector = Icons.Rounded.Wifi,
                    contentDescription = null,
                    tint = LightBlue,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(2.dp)
                )
            }
            Facilities.BREAKFASTINCLUDED.name -> {
                Icon(
                    imageVector = Icons.Rounded.FreeBreakfast,
                    contentDescription = null,
                    tint = LightBlue,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(2.dp)

                )
            }
            Facilities.SANITISATIONBADGE.name -> {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.health_and_safety),
                        contentDescription = null,
                        tint = LightBlue,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(2.dp)

                    )
                    Text(
                        text = "Covid-19 safe",
                        fontSize = 10.sp,
                        color = LightBlue,
                        modifier = Modifier.padding(top = 3.dp, end = 2.dp)
                    )
                }
            }
        }
    }
}


enum class Facilities {
    FREEWIFI,
    FREEINTERNETACCESS,
    FREECITYTOUR,
    BREAKFASTINCLUDED,
    SANITISATIONBADGE

}

enum class FacilityCategory {
    FACILITYCATEGORYFREE,
    FACILITYCATEGORYGENERAL,
    FACILITYCATEGORYSERVICES,
    FACILITYCATEGORYFOODANDDRINK,
    FACILITYCATEGORYENTERTAINMENT,
}

