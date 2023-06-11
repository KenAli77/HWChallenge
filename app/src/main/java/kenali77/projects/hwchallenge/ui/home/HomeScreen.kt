package kenali77.projects.hwchallenge.ui.home

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kenali77.projects.hwchallenge.ui.components.SearchBar
import kenali77.projects.hwchallenge.ui.home.components.PropertiesListView
import kenali77.projects.hwchallenge.ui.home.components.Toolbar
import kenali77.projects.hwchallenge.ui.home.components.isScrolled
import kenali77.projects.hwchallenge.ui.theme.DarkPurple
import kenali77.projects.hwchallenge.ui.theme.LightPurple

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val lazyListState = rememberLazyListState()
    val state = viewModel.state


    Surface(color = Color.Transparent) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            AnimatedVisibility(visible = !lazyListState.isScrolled) {

                Toolbar(
                    modifier = Modifier.padding(20.dp),
                    lazyListState = lazyListState
                )
            }

            Surface(color = LightPurple) {
                SearchBar(
                    modifier = Modifier.padding(20.dp)
                )
            }
            state.properties?.let {
                PropertiesListView(
                    properties = it,
                    onItemClick = {},
                    modifier = Modifier,
                    lazyListState = lazyListState
                )

            }


        }
    }

}

