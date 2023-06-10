package kenali77.projects.hwchallenge.ui.home.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kenali77.projects.hwchallenge.R
import kenali77.projects.hwchallenge.ui.theme.DarkPurple
import kenali77.projects.hwchallenge.ui.theme.LightPurple

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
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(tween(500))
            .height(70.dp),
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
