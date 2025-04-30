package com.pdmtaller2.JuanCastellanos_00026223.ui.theme.navigation

sealed class Screen(val route: String) {
    object RestaurantList : Screen("restaurant_list")
    object RestaurantDetail : Screen("restaurant_detail/{restaurantId}") {
        fun createRoute(restaurantId: Int): String = "restaurant_detail/$restaurantId"
    }
}