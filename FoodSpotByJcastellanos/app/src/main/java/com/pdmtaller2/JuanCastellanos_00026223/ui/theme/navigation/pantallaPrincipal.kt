package com.pdmtaller2.JuanCastellanos_00026223.ui.theme.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.pdmtaller2.JuanCastellanos_00026223.data.Restaurant
import com.pdmtaller2.JuanCastellanos_00026223.ui.theme.screens.RestaurantListScreen
import com.pdmtaller2.JuanCastellanos_00026223.ui.theme.screens.RestaurantDetailScreen

@Composable
fun AppNavHost(restaurants: List<Restaurant>) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Listado.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Listado.route) {
                RestaurantListScreen(
                    restaurants = restaurants,
                    onRestaurantClick = { restaurantId ->
                        navController.navigate("restaurantDetail/$restaurantId")
                    }
                )
            }

            composable(BottomNavItem.Busqueda.route) {
                // TODO: Tu pantalla de búsqueda
            }

            composable(BottomNavItem.Ordenes.route) {
                // TODO: Tu pantalla de órdenes
            }

            composable(
                route = "restaurantDetail/{restaurantId}",
                arguments = listOf(navArgument("restaurantId") { type = NavType.IntType })
            ) { backStackEntry ->
                val restaurantId = backStackEntry.arguments?.getInt("restaurantId")
                val restaurant = restaurants.find { it.id == restaurantId }
                if (restaurant != null) {
                    RestaurantDetailScreen(restaurant = restaurant)
                }
            }
        }
    }
}

