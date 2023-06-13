package kenali77.projects.hwchallenge.domain.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
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
    fun getFacilityIcon(size:Int = 20,padding:Int = 2) {
        Surface(
            shape = RoundedCornerShape(size.dp),
            color = LightBlue.copy(0.1f),
            modifier = Modifier.padding(end = 2.dp)
        ) {
        when (id) {
            Facilities.FREEWIFI.name -> {
                Icon(
                    imageVector = Icons.Rounded.Wifi,
                    contentDescription = null,
                    tint = LightBlue,
                    modifier = Modifier
                        .size(size.dp)
                        .padding(padding.dp)
                )
            }
            Facilities.BREAKFASTINCLUDED.name -> {
                Icon(
                    imageVector = Icons.Rounded.FreeBreakfast,
                    contentDescription = null,
                    tint = LightBlue,
                    modifier = Modifier
                        .size(size.dp)
                        .padding(padding.dp)

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
                            .size(size.dp)
                            .padding(padding.dp)

                    )
                    Text(
                        text = "Covid-19 safe",
                        fontSize = 10.sp,
                        color = LightBlue,
                        modifier = Modifier.padding(top = 3.dp, end = 2.dp)
                    )
                }
            }
            Facilities.FREEINTERNETACCESS.name -> {
                Icon(
                    imageVector = Icons.Rounded.Wifi,
                    contentDescription = null,
                    tint = LightBlue,
                    modifier = Modifier
                        .size(size.dp)
                        .padding(padding.dp)

                )
            }
            Facilities.LINENINCLUDED.name -> {
                Icon(
                    imageVector = Icons.Rounded.Bed,
                    contentDescription = null,
                    tint = LightBlue,
                    modifier = Modifier
                        .size(size.dp)
                        .padding(padding.dp)

                )
            }
            Facilities.FREECITYMAPS.name -> {
                Icon(
                    imageVector = Icons.Rounded.Map,
                    contentDescription = null,
                    tint = LightBlue,
                    modifier = Modifier
                        .size(size.dp)
                        .padding(padding.dp)

                )
            }
            Facilities.FREECITYTOUR.name -> {
                Icon(
                    imageVector = Icons.Rounded.Tour,
                    contentDescription = null,
                    tint = LightBlue,
                    modifier = Modifier
                        .size(size.dp)
                        .padding(padding.dp)

                )
            }
            else -> {
            Icon(
                imageVector = Icons.Rounded.TaskAlt,
                contentDescription = null,
                tint = LightBlue,
                modifier = Modifier
                    .size(size.dp)
                    .padding(padding.dp)

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
    SANITISATIONBADGE,
    LINENINCLUDED,
    FREECITYMAPS,


}

enum class FacilityCategory {
    FACILITYCATEGORYFREE,
    FACILITYCATEGORYGENERAL,
    FACILITYCATEGORYSERVICES,
    FACILITYCATEGORYFOODANDDRINK,
    FACILITYCATEGORYENTERTAINMENT,
}

