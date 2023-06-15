package kenali77.projects.hwchallenge.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kenali77.projects.hwchallenge.ui.components.SearchBar
import kenali77.projects.hwchallenge.ui.home.components.*
import kenali77.projects.hwchallenge.ui.navigation.Screens
import kenali77.projects.hwchallenge.ui.theme.LightPurple
import kenali77.projects.hwchallenge.ui.theme.Orange

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navHostController: NavHostController) {
    val lazyListState = rememberLazyListState()
    val state = viewModel.state
    val searchQuery by viewModel.searchQuery.collectAsState()
    val properties by viewModel.properties.collectAsState()
    var isHintVisible by remember { mutableStateOf(true) }


    LaunchedEffect(key1 = searchQuery) {
        isHintVisible = searchQuery.isEmpty()
    }
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
                    modifier = Modifier.padding(20.dp),
                    textValue = searchQuery,
                    onValueChange = viewModel::onSearchQueryChange,
                    onSearch = {},
                    isHintVisible = isHintVisible
                )
            }

            properties?.let {
                if (it.isEmpty()) {
                    NoDataView(
                        modifier = Modifier,
                        text = "No matching results found. Please try a different search term"
                    )
                } else {
                    PropertiesListView(
                        properties = it,
                        onItemClick = {
                            navHostController.navigate(Screens.DetailScreen.route + "?propertyId=${it.id}")
                        },
                        modifier = Modifier,
                        lazyListState = lazyListState
                    )

                }
            } ?: if (!state.loading && !state.error.isNullOrBlank()) {
                NoDataView(modifier = Modifier, text = "We couldn't find any data")
            } else if(state.loading) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        color = Orange,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(50.dp)
                    )
                }
            } else if (!state.error.isNullOrBlank()) {
                NoDataView(text = state.error)
            }


        }
    }
}

