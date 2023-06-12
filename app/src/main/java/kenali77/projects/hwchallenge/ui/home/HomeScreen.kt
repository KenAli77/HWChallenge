package kenali77.projects.hwchallenge.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kenali77.projects.hwchallenge.ui.components.SearchBar
import kenali77.projects.hwchallenge.ui.home.components.PropertiesListView
import kenali77.projects.hwchallenge.ui.home.components.Toolbar
import kenali77.projects.hwchallenge.ui.home.components.isScrolled
import kenali77.projects.hwchallenge.ui.navigation.Screens
import kenali77.projects.hwchallenge.ui.theme.LightPurple

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(),navHostController: NavHostController) {
    val lazyListState = rememberLazyListState()
    val state = viewModel.state

    Surface(color = MaterialTheme.colors.background) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AnimatedVisibility(visible = !lazyListState.isScrolled) {
                Toolbar(
                    modifier = Modifier.padding(20.dp),
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
                    onItemClick = {
                        navHostController.navigate(Screens.DetailScreen.route + "?propertyId=${it.id}")},
                    modifier = Modifier,
                    lazyListState = lazyListState
                )
            }
        }
    }
}

