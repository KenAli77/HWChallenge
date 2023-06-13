package kenali77.projects.hwchallenge.ui.propertyDetails.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.IosShare
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kenali77.projects.hwchallenge.R
import kenali77.projects.hwchallenge.data.local.database.PropertyModel
import kenali77.projects.hwchallenge.domain.model.FacilityX
import kenali77.projects.hwchallenge.domain.model.ImagesGallery
import kenali77.projects.hwchallenge.ui.theme.Grey
import kenali77.projects.hwchallenge.ui.theme.Yellow
import kenali77.projects.hwchallenge.ui.utils.bigDecimal
import kenali77.projects.hwchallenge.ui.utils.bitmapFromVector

@Composable
fun ImageSlider(images: List<ImagesGallery>, modifier: Modifier = Modifier) {
    LazyRow(contentPadding = PaddingValues(8.dp), modifier = modifier.fillMaxWidth()) {
        itemsIndexed(images) { index, item ->
            val roundedCornersStart = if (index == 0) 20.dp else 0.dp
            val roundedCornersEnd = if (index == images.size - 1) 20.dp else 0.dp
            Surface(
                modifier = Modifier
                    .height(220.dp)
                    .width(250.dp)
                    .padding(horizontal = 5.dp),
                shape = RoundedCornerShape(
                    topStart = roundedCornersStart,
                    topEnd = roundedCornersEnd,
                    bottomEnd = roundedCornersEnd,
                    bottomStart = roundedCornersStart
                )
            ) {
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

@Composable
fun TopBar(
    property: PropertyModel,
    onBackPressed: () -> Unit = {},
    onShare: (property: PropertyModel) -> Unit = {},
    modifier: Modifier
) {
    ConstraintLayout(
        modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        val (backNav, name, share) = createRefs()

        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .size(25.dp)
                .constrainAs(backNav) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }) { onBackPressed() },
        )

        Text(
            text = property.name,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.constrainAs(name) {
                top.linkTo(parent.top)
                start.linkTo(backNav.end, 20.dp)
                bottom.linkTo(parent.bottom)
            })

        Icon(
            imageVector = Icons.Outlined.IosShare,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .size(25.dp)
                .rotate(90f)
                .constrainAs(share) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }) { onShare(property) })
    }
}

@Composable
fun PropertyInfoBar(property: PropertyModel, modifier: Modifier = Modifier) {

    ConstraintLayout(modifier = modifier.fillMaxWidth()) {

        val (type, name, location, rating, reviewCount) = createRefs()
        Text(
            text = property.type.lowercase().replaceFirstChar { it.uppercase() },
            fontSize = 12.sp,
            color = Grey,
            modifier = Modifier.constrainAs(type) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            })
        Text(
            text = property.name,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier.constrainAs(name) {
                start.linkTo(name.start)
                top.linkTo(type.bottom)
            })
        Row(
            modifier = Modifier.constrainAs(location) {
                top.linkTo(name.bottom)
                start.linkTo(name.start)
            },
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .alignByBaseline()
            )
            Text(
                text = "Dublin", fontSize = 15.sp,
                modifier = Modifier.alignByBaseline(),
                textAlign = TextAlign.Center
            )
        }

        Row(modifier = Modifier.constrainAs(rating) {
            end.linkTo(parent.end)
            top.linkTo(name.top)
        }, verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Rounded.Star,
                contentDescription = null,
                tint = Yellow,
                modifier = Modifier
                    .size(35.dp)
            )
            Text(
                text = "${property.overallRating.getDecimalRating()}",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier.alignByBaseline()

            )
        }

        Text(
            text = property.overallRating.numberOfRatings + " reviews",
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.constrainAs(reviewCount) {
                top.linkTo(rating.bottom)
                end.linkTo(parent.end)
            })
    }

}

@Composable
fun FacilitiesBar(facilities: List<FacilityX>, modifier: Modifier = Modifier) {
    LazyRow(modifier = modifier, contentPadding = PaddingValues(horizontal = 5.dp)) {
        items(facilities) {
            Row(
                modifier = Modifier.padding(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                it.getFacilityIcon()
                Text(text = it.name, fontSize = 12.sp)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AboutSection(overview: String, modifier: Modifier) {

    var textOverflow by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {

        Text(text = "About", fontSize = 25.sp, fontWeight = FontWeight.Bold)

        Text(
            text = overview,
            fontSize = 15.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 4,
            onTextLayout = { textLayoutResult ->
                textOverflow = textLayoutResult.hasVisualOverflow
            })

        if (textOverflow) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.End),
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Read more",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textDecoration = TextDecoration.Underline
                )

                Icon(imageVector = Icons.Rounded.ChevronRight, contentDescription = null)

            }
        }


    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FacilitiesBox(facilities: List<FacilityX>, modifier: Modifier = Modifier) {

    FlowColumn(
        maxItemsInEachColumn = 3,
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        repeat(facilities.size) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(5.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                facilities[item].getFacilityIcon(size = 30, padding = 5)
                Text(facilities[item].name)
            }
        }

    }

}

@Composable
fun ReviewSection(property: PropertyModel, modifier: Modifier = Modifier) {

    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = "Reviews", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = modifier.height(10.dp))
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.Star,
                contentDescription = null,
                tint = Yellow,
                modifier = Modifier
                    .size(30.dp)
            )
            Text(
                text = "${property.overallRating.getDecimalRating()}",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier.alignByBaseline()

            )

            Text(text = property.overallRating.getQualitativeScore(), fontSize = 18.sp)

            Text(text = "(${property.overallRating.numberOfRatings})")


        }

        RatingBreakdownItem(
            value = property.ratingBreakdown.value,
            name = "Value for money",
            modifier = Modifier
        )
        RatingBreakdownItem(
            value = property.ratingBreakdown.security,
            name = "Security",
            modifier = Modifier
        )
        RatingBreakdownItem(
            value = property.ratingBreakdown.`fun`,
            name = "Atmosphere",
            modifier = Modifier
        )
        RatingBreakdownItem(
            value = property.ratingBreakdown.clean,
            name = "Cleanliness",
            modifier = Modifier
        )
        RatingBreakdownItem(
            value = property.ratingBreakdown.staff,
            name = "Staff",
            modifier = Modifier
        )
        RatingBreakdownItem(
            value = property.ratingBreakdown.location,
            name = "Location",
            modifier = Modifier
        )
        RatingBreakdownItem(
            value = property.ratingBreakdown.facilities,
            name = "Facilities",
            modifier = Modifier
        )
    }


}

@Composable
fun RatingBreakdownItem(value: Int, name: String, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name, fontSize = 14.sp)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.End)
        ) {
            LinearProgressIndicator(
                backgroundColor = Grey.copy(0.2f),
                color = Yellow,
                modifier = Modifier
                    .width(100.dp)
                    .height(7.dp)
                    .clip(RoundedCornerShape(10.dp)),
                progress = (value.toFloat() / 100),
            )
            Text(
                text = "${value.bigDecimal()}",
                modifier = Modifier.alignByBaseline(),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun MapView(lat: Double, long: Double,modifier: Modifier=Modifier) {
    val location = LatLng(
        lat,
        long
    )
    Log.e("latLng",location.toString())
    val context = LocalContext.current

    val uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                zoomControlsEnabled = false,
                myLocationButtonEnabled = false,
                scrollGesturesEnabled = false,
                scrollGesturesEnabledDuringRotateOrZoom = false,
                rotationGesturesEnabled = false,
                tiltGesturesEnabled = false,
                zoomGesturesEnabled = false
            )
        )
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 15f)
    }
    val markerState = rememberMarkerState(position = location)
    GoogleMap(
        modifier = modifier.fillMaxWidth().height(250.dp),
        cameraPositionState = cameraPositionState,
        uiSettings = uiSettings
    ) {
        Marker(
            state = markerState,
            flat = true,
            anchor = Offset(0.5f, 0.5f),

        )
    }
}