package kenali77.projects.hwchallenge.ui.home.components

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FreeBreakfast
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.Wifi
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kenali77.projects.hwchallenge.R
import kenali77.projects.hwchallenge.domain.model.*
import kenali77.projects.hwchallenge.ui.theme.*

fun getWelcomeText(): String {
    val content = listOf(
        "Ready for another adventure? Obvs!",
        "Been dreaming of anywhere lately?",
        "Fancy a new adventure?",
        "Let the adventures begin!"
    )
    return content.random()
}

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(tween(500))
            .height(70.dp),
//            .animateContentSize(animationSpec = tween(400)),
        color = LightPurple

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = getWelcomeText(),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier.fillMaxWidth(0.66f)
            )

            Surface(
                shape = CircleShape, color = DarkPurple, border = BorderStroke(1.dp, Color.White)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_account),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(10.dp)
                )
            }
        }
    }
}

val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0


@Composable
fun PropertiesListView(
    modifier: Modifier = Modifier,
    properties: Properties,
    onItemClick: (property: Property) -> Unit,
    lazyListState: LazyListState = rememberLazyListState()
) {

    var topCornersRadius by mutableStateOf(0.dp)

    topCornersRadius = if (lazyListState.isScrolled) {
        0.dp
    } else {
        20.dp
    }

    LazyColumn(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = topCornersRadius,
                    topEnd = topCornersRadius
                )
            )
            .background(
                Color.White
            ), contentPadding = PaddingValues(10.dp), state = lazyListState
    ) {

        items(properties.properties) { item: Property ->
            PropertyItemView(property = item)
        }
    }

}


@Composable
fun PropertyItemView(property: Property) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(
            1.dp,
            Grey.copy(0.3f)
        )

    ) {
        Box(Modifier.fillMaxSize()) {
            ConstraintLayout() {
                val (imageGallery, name, rating, info, features, privatePriceBox, dormPriceBox) = createRefs()

                ListItemImageSlider(
                    images = property.imagesGallery,
                    modifier = Modifier.constrainAs(imageGallery) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })

                Log.e("PROPTERCNA", property.name)
                Text(
                    text = property.name,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .constrainAs(name) {
                            top.linkTo(imageGallery.bottom, 5.dp)
                            start.linkTo(parent.start)
                        }
                        .padding(horizontal = 10.dp, vertical = 0.dp),
                    fontSize = 20.sp,
                    color = Color.Black
                )

                RatingBar(modifier = Modifier
                    .constrainAs(rating) {
                        top.linkTo(name.bottom)
                        start.linkTo(parent.start)

                    }
                    .padding(horizontal = 10.dp), rating = property.overallRating)

                Text(
                    text = "${
                        property.type.lowercase().replaceFirstChar { it.uppercase() }
                    } - ${property.distance.value}${property.distance.units} from city centre",
                    modifier = Modifier
                        .constrainAs(info) {
                            top.linkTo(rating.bottom)
                            start.linkTo(parent.start)
                        }
                        .padding(horizontal = 10.dp),
                )

                FacilitiesBar(
                    facilities = property.facilities.first { it.id == FacilityCategory.FACILITYCATEGORYFREE.name }.facilities,
                    modifier = Modifier
                        .constrainAs(features) {
                            start.linkTo(parent.start)
                            top.linkTo(info.bottom)
                        }
                        .padding(horizontal = 10.dp, vertical = 5.dp))

//            PriceBox(property = property)
            }
            if (property.isFeatured) {
                Surface(
                    color = LightPurple,
                    shape = CutCornerShape(bottomEnd = 30.dp),
                    modifier = Modifier
                        .align(
                            Alignment.TopStart
                        )
                        .padding(top = 20.dp)
                ) {
                    Row() {
                        Text(
                            text = "Featuerd Property",
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            modifier = Modifier.alignByBaseline().padding(horizontal = 3.dp, vertical = 0.dp)
                        ) 
                        Spacer(modifier = Modifier.width(18.dp))
                    }
                    
                }
            }
        }


    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListItemImageSlider(images: List<ImagesGallery>, modifier: Modifier) {

    val pagerState = rememberPagerState {
        images.size
    }

    Box(
        modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            state = pagerState,
            pageSpacing = 10.dp,
            contentPadding = PaddingValues(8.dp),
            pageSize = PageSize.Fill,
            key = { images[it].suffix },
        ) { index ->

            val image = images[index]

            Log.e("iamge url", image.prefix + image.suffix)

            GlideImage(
                imageModel = { "https://${image.prefix}${image.suffix}" },
                imageOptions = ImageOptions(
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,

                    ),
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .fillMaxWidth(),

                )

        }

        Row(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(images.size) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.White else Color.White.copy(0.5f)
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)

                )
            }
        }

    }


}


@Composable
fun ImageSlider() {

}

@Composable
fun RatingBar(modifier: Modifier, rating: OverallRating) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.Top
    ) {

        Icon(
            imageVector = Icons.Rounded.Star,
            contentDescription = null,
            tint = Yellow,
            modifier = Modifier.padding(top = 2.dp)
        )
        Text(
            text = "${rating.getDecimalRating()}",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.alignByBaseline()

        )
        Text(text = rating.getQualitativeScore(), modifier = Modifier.alignByBaseline())
        Text(
            text = "(${rating.numberOfRatings})",
            modifier = Modifier
                .padding(start = 1.dp)
                .alignByBaseline()
        )

    }

}

@Composable
fun FacilitiesBar(facilities: List<FacilityX>, modifier: Modifier = Modifier) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        facilities.forEach { facility ->

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = LightBlue.copy(0.2f),
                modifier = Modifier.padding(end = 2.dp)
            ) {
                when (facility.id) {
                    Facilities.FREEWIFI.name -> {
                        Icon(
                            imageVector = Icons.Rounded.Wifi,
                            contentDescription = null,
                            tint = LightBlue,
                            modifier = Modifier
                                .size(20.dp)
                                .padding(1.dp)
                        )
                    }
                    Facilities.BREAKFASTINCLUDED.name -> {
                        Icon(
                            imageVector = Icons.Rounded.FreeBreakfast,
                            contentDescription = null,
                            tint = LightBlue,
                            modifier = Modifier
                                .size(20.dp)
                                .padding(1.dp)

                        )
                    }
                }

            }

        }

    }
}

@Composable
fun PriceBox(property: Property, modifier: Modifier = Modifier) {

    Surface(color = Magenta, shape = CutCornerShape(bottomStart = 30.dp)) {
        Text(text = "ajoooo")
    }

}