package kenali77.projects.hwchallenge.ui.propertyDetails

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
            Box(modifier = Modifier.fillMaxSize()) {
                TopBar(
                    property,
                    onBackPressed = { navHostController.navigateUp() },
                    onShare = {},
                    modifier = Modifier
                        .height(50.dp)
                        .padding(start = 12.dp, end = 12.dp, top = 8.dp)
                        .align(Alignment.TopCenter)
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 60.dp)
                        .verticalScroll(scrollState, enabled = true)
                        ,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
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
                    Divider(
                        thickness = 1.dp, color = Grey.copy(0.3f), modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                    )
                    CheckInOutBox(checkIn = "14:00 - 23:00", checkOut = "11:00", modifier = Modifier.padding(horizontal = 12.dp))
                    Divider(
                        thickness = 1.dp, color = Grey.copy(0.3f), modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                    )
                    FacilitiesBox(
                        facilities = property.facilities[0].facilities,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                    )
                    Spacer(Modifier.height(10.dp))
                    ReviewSection(
                        property = property,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    MapView(
                        lat = property.latitude,
                        long = property.longitude,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                    LocationData(modifier = Modifier.padding(10.dp), address1 = property.address1, address2 = property.address2)
                    Spacer(Modifier.height(70.dp))

                }

                ChooseRoomSection(
                    lowestAvgPrice = property.LowestAveragePricePerNight!!.value,
                    modifier = Modifier.align(
                        Alignment.BottomCenter
                    ).height(75.dp)
                )
            }

        }
    }

}