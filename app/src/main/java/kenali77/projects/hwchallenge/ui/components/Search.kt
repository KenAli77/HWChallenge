package kenali77.projects.hwchallenge.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import kenali77.projects.hwchallenge.R
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import kenali77.projects.hwchallenge.ui.theme.Grey
import kenali77.projects.hwchallenge.ui.theme.Orange
import kenali77.projects.hwchallenge.ui.utils.customClickable

/**
 * Shared UI components
 */

@Composable
fun SearchBar(
    modifier: Modifier,
    onClick: () -> Unit = {},
    textValue: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    isHintVisible:Boolean
) {
    Surface(
        shape = RoundedCornerShape(25.dp),
        color = Color.White,
        modifier = modifier
            .customClickable { onClick() },
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
                start.linkTo(icon.end)
                bottom.linkTo(parent.bottom)
            }
            ) {


                BasicTextField(
                    value = textValue,
                    onValueChange = { onValueChange(it) },
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )
                if (isHintVisible) {
                    Text(
                        text = "Where next to?",
                        color = Grey,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )

                }
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
                    modifier = Modifier
                        .padding(12.dp)
                        .customClickable {
                            onSearch()
                        }

                )
            }
        }

    }

}