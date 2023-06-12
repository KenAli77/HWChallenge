package kenali77.projects.hwchallenge.ui.propertyDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kenali77.projects.hwchallenge.domain.model.Property
import kenali77.projects.hwchallenge.ui.propertyDetails.components.ImageSlider

@Composable
fun PropertyDetailsScreen(propertyId:String){

    Surface(Modifier.fillMaxSize(), color = Color.White) {

        Column(modifier = Modifier.fillMaxSize()) {
            ImageSlider()
        }

    }
}