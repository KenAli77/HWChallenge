package kenali77.projects.hwchallenge.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import kenali77.projects.hwchallenge.R
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import kenali77.projects.hwchallenge.ui.theme.Grey
import kenali77.projects.hwchallenge.ui.theme.Orange

@Composable
fun SearchBar(modifier: Modifier, onClick: () -> Unit = {}) {

    Surface(
        shape = RoundedCornerShape(25.dp),
        color = Color.White,
        modifier = modifier
            .clickable { onClick() },
        border = BorderStroke(1.2.dp, Grey)
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(50.dp)
        ) {
            val (icon, placeholder, button) = createRefs()

            Icon(
                painter = painterResource(id = R.drawable.home_pin),
                contentDescription = null,
                tint = Color.DarkGray,
                modifier = Modifier
                    .constrainAs(icon) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(15.dp))

            Box(contentAlignment = Alignment.Center, modifier = Modifier.constrainAs(placeholder) {
                top.linkTo(parent.top)
                start.linkTo(icon.end, 8.dp)
                bottom.linkTo(parent.bottom)
            }
            ) {

                Text(
                    text = "Where to next?",
                    color = Grey,
                    textAlign = TextAlign.Center,

                    )
            }

            Surface(
                shape = RoundedCornerShape(15.dp),
                color = Orange,
                modifier = Modifier.constrainAs(button) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },

                ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(12.dp)

                )
            }
        }

    }

}