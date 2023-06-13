package kenali77.projects.hwchallenge.ui.propertyDetails.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.IosShare
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kenali77.projects.hwchallenge.data.local.database.PropertyModel
import kenali77.projects.hwchallenge.domain.model.ImagesGallery
import kenali77.projects.hwchallenge.ui.theme.Grey
import kenali77.projects.hwchallenge.ui.theme.Yellow

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
            Icon(imageVector = Icons.Outlined.LocationOn, contentDescription = null, modifier = Modifier
                .size(20.dp)
                .alignByBaseline())
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

        Text(text = property.overallRating.numberOfRatings+" reviews", textDecoration = TextDecoration.Underline, modifier = Modifier.constrainAs(reviewCount){
            top.linkTo(rating.bottom)
            end.linkTo(parent.end)
        })
    }

}

@Composable
fun FacilitiesBar(property: PropertyModel, modifier: Modifier=Modifier){
    LazyRow(modifier = modifier, contentPadding = PaddingValues(horizontal = 5.dp)){
        items(property.facilities[0].facilities){
            Row(modifier = Modifier.padding(5.dp), horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                it.getFacilityIcon()
                Text(text = it.name, fontSize = 12.sp)
            }
        }
    }
}