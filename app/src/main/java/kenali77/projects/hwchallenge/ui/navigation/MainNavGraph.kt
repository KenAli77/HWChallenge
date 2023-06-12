package kenali77.projects.hwchallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kenali77.projects.hwchallenge.domain.model.Property
import kenali77.projects.hwchallenge.ui.home.HomeScreen
import kenali77.projects.hwchallenge.ui.home.HomeViewModel
import kenali77.projects.hwchallenge.ui.propertyDetails.PropertyDetailViewModel
import kenali77.projects.hwchallenge.ui.propertyDetails.PropertyDetailsScreen
import kenali77.projects.hwchallenge.ui.theme.DarkPurple

@Composable
fun MainNavGraph(navHostController: NavHostController) {
    val systemUiController = rememberSystemUiController()
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()

    when (navBackStackEntry?.destination?.route) {
        Screens.HomeScreen.route -> {
            systemUiController.setStatusBarColor(
                color = DarkPurple,
            )
        }
        Screens.DetailScreen.route -> {
            systemUiController.setStatusBarColor(
                color = Color.Gray.copy(0.4f),
            )
        }
    }

    NavHost(
        navController = navHostController,
        startDestination = "main_route"
    ) {
        navigation(startDestination = Screens.HomeScreen.route, route = "main_route") {
            composable(route = Screens.HomeScreen.route) { navBackStackEntry ->
                val homeViewModel = hiltViewModel<HomeViewModel>(navBackStackEntry)
                HomeScreen(viewModel = homeViewModel, navHostController = navHostController)
            }
            composable(
                route = Screens.DetailScreen.route + "?propertyId={propertyId}", arguments = listOf(
                    navArgument("propertyId") {
                        type = NavType.IntType
                        defaultValue = -1
                    })
            ) { navBackStackEntry ->
                val propertyId = navBackStackEntry.arguments?.getInt("propertyId")!!
                val propertyDetailViewModel = hiltViewModel<PropertyDetailViewModel>(navBackStackEntry)
                PropertyDetailsScreen(propertyId = propertyId,propertyDetailViewModel)
            }

        }
    }
}