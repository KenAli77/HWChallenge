package kenali77.projects.hwchallenge.ui.propertyDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import kenali77.projects.hwchallenge.ui.propertyDetails.components.ImageSlider

@Composable
fun PropertyDetailsScreen(propertyId:Int,viewModel: PropertyDetailViewModel = hiltViewModel()){

    val state = viewModel.state
    state.data?.let { property ->
        Surface(Modifier.fillMaxSize(), color = Color.White) {

            Column(modifier = Modifier.fillMaxSize()) {
                ImageSlider(property)
            }

        }
    }

}