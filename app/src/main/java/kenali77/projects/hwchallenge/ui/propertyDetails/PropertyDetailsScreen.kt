package kenali77.projects.hwchallenge.ui.propertyDetails

import android.widget.Space
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kenali77.projects.hwchallenge.ui.propertyDetails.components.*
import kenali77.projects.hwchallenge.ui.theme.Grey

@Composable
fun PropertyDetailsScreen(
    propertyId: Int,
    viewModel: PropertyDetailViewModel = hiltViewModel(),
    navHostController: NavHostController = rememberNavController(
    )
) {

    val state = viewModel.state
    val scrollState = rememberScrollState()
    state.data?.let { property ->
        Surface(Modifier.fillMaxSize(), color = Color.White) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState, enabled = true),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TopBar(
                    property,
                    onBackPressed = { navHostController.navigateUp() },
                    onShare = {},
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 8.dp)
                )
                ImageSlider(property.imagesGallery)
                PropertyInfoBar(
                    property = property,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                FacilitiesBar(
                    facilities = property.facilities[0].facilities,
                    modifier = Modifier.fillMaxWidth()
                )
                AboutSection(
                    overview = property.overview,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                Divider(thickness = 1.dp, color = Grey.copy(0.3f), modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp))
                FacilitiesBox(
                    facilities = property.facilities[0].facilities,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                )
                Spacer(Modifier.height(10.dp))
                ReviewSection(property=property,modifier=Modifier.padding(horizontal = 12.dp))
                MapView(lat = property.latitude, long = property.longitude, modifier = Modifier.padding(vertical = 10.dp))
            }

        }
    }

}