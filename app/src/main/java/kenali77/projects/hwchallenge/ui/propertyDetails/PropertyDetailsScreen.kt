package kenali77.projects.hwchallenge.ui.propertyDetails

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kenali77.projects.hwchallenge.ui.propertyDetails.components.ImageSlider
import kenali77.projects.hwchallenge.ui.propertyDetails.components.TopBar

@Composable
fun PropertyDetailsScreen(
    propertyId: Int,
    viewModel: PropertyDetailViewModel = hiltViewModel(),
    navHostController: NavHostController = rememberNavController(
    )
) {

    val state = viewModel.state
    state.data?.let { property ->
        Surface(Modifier.fillMaxSize(), color = Color.White) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                TopBar(property, onBackPressed = { navHostController.navigateUp() }, onShare = {},modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 8.dp))
                ImageSlider(property.imagesGallery)
            }

        }
    }

}