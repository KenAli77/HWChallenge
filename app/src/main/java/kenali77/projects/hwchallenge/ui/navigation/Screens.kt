package kenali77.projects.hwchallenge.ui.navigation

sealed class Screens(val route:String, val title:String){

    object HomeScreen:Screens(route = "home_screen", title = "Home")
    object DetailScreen:Screens(route = "detail_screen/{property}", title = "Property Details")
}
