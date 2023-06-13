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
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kenali77.projects.hwchallenge.data.local.database.PropertyModel
import kenali77.projects.hwchallenge.domain.model.ImagesGallery
import kenali77.projects.hwchallenge.domain.model.Property

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