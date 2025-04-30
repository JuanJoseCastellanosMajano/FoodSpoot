package com.pdmtaller2.JuanCastellanos_00026223.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pdmtaller2.JuanCastellanos_00026223.ui.theme.screens.OrdersScreen
import com.pdmtaller2.JuanCastellanos_00026223.ui.theme.screens.RestaurantDetailScreen
import com.pdmtaller2.JuanCastellanos_00026223.ui.theme.screens.RestaurantListScreen
import com.pdmtaller2.JuanCastellanos_00026223.ui.theme.screens.SearchScreen

// Definición de las pantallas/rutas de la aplicación
sealed class AppScreens(val route: String) {
    object ListScreen : AppScreens("list_screen")
    object DetailScreen : AppScreens("detail_screen/{restaurantId}") {
        fun createRoute(restaurantId: Int) = "detail_screen/$restaurantId"
    }
    object SearchScreen : AppScreens("search_screen")
    object OrdersScreen : AppScreens("orders_screen")
}

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppScreens.ListScreen.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Pantalla de Listado
        composable(AppScreens.ListScreen.route) {
            RestaurantListScreen(
                onItemClick = { id ->
                    navController.navigate(AppScreens.DetailScreen.createRoute(id))
                },
                onSearchClick = {
                    navController.navigate(AppScreens.SearchScreen.route)
                },
                onOrdersClick = {
                    navController.navigate(AppScreens.OrdersScreen.route)
                }
            )
        }

        // Pantalla de Detalle
        composable(
            route = AppScreens.DetailScreen.route,
            arguments = listOf(navArgument("restaurantId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val restaurantId = backStackEntry.arguments?.getInt("restaurantId") ?: 0
            RestaurantDetailScreen(
                restaurantId = restaurantId,
                onBack = { navController.popBackStack() },
                onOrderClick = { dish, restaurant ->
                    // Lógica para agregar al carrito
                    navController.navigate(AppScreens.OrdersScreen.route)
                }
            )
        }

        // Pantalla de Búsqueda
        composable(AppScreens.SearchScreen.route) {
            SearchScreen(
                onBack = { navController.popBackStack() },
                onItemClick = { id ->
                    navController.navigate(AppScreens.DetailScreen.createRoute(id))
                }
            )
        }

        // Pantalla de Órdenes/Carrito
        composable(AppScreens.OrdersScreen.route) {
            OrdersScreen(
                onBack = { navController.popBackStack() },
                onConfirmOrder = {
                    // Lógica para confirmar pedido
                    navController.popBackStack()
                }
            )
        }
    }
}