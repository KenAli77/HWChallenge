package kenali77.projects.hwchallenge.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import java.math.BigDecimal
import java.math.RoundingMode

fun Int.bigDecimal(): BigDecimal {
    return (this.toFloat() * 0.10f).toBigDecimal().setScale(1, RoundingMode.HALF_UP)
}


@Composable
fun Modifier.customClickable(onClick: () -> Unit):Modifier {
    return this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}