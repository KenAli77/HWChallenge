package kenali77.projects.hwchallenge.ui.propertyDetails.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kenali77.projects.hwchallenge.domain.model.Property

@Composable
fun ImageSlider(property: Property) {
    LazyRow(contentPadding = PaddingValues(8.dp)) {
        items(property.imagesGallery) { item ->
            Surface(modifier = Modifier.height(250.dp).width(300.dp)) {
                GlideImage(
                    imageModel = { "https://${item.prefix}${item.suffix}" },
                    imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop
                    )
                )
            }
        }
    }
}