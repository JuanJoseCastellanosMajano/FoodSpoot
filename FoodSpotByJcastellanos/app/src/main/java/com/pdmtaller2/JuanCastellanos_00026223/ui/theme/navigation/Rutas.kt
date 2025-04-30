package com.pdmtaller2.JuanCastellanos_00026223.ui.theme.navigation

sealed class AppScreens(val route: String) {
    object ListScreen : AppScreens("list_screen")
    object DetailScreen : AppScreens("detail_screen/{restaurantId}") {
        fun createRoute(restaurantId: Int) = "detail_screen/$restaurantId"
    }
    object SearchScreen : AppScreens("search_screen")
    object OrdersScreen : AppScreens("orders_screen")
}