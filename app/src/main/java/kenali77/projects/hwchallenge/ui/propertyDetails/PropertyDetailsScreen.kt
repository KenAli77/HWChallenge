package kenali77.projects.hwchallenge.ui.propertyDetails

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kenali77.projects.hwchallenge.ui.propertyDetails.components.FacilitiesBar
import kenali77.projects.hwchallenge.ui.propertyDetails.components.ImageSlider
import kenali77.projects.hwchallenge.ui.propertyDetails.components.PropertyInfoBar
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
                PropertyInfoBar(property = property, modifier = Modifier.padding(horizontal = 10.dp))
                FacilitiesBar(property=property,modifier=Modifier.fillMaxWidth())
            }

        }
    }

}