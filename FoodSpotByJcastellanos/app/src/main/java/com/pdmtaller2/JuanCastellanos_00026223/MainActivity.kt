package com.pdmtaller2.JuanCastellanos_00026223
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pdmtaller2.JuanCastellanos_00026223.data.Restaurant
import com.pdmtaller2.JuanCastellanos_00026223.data.restaurantsinfo
import com.pdmtaller2.JuanCastellanos_00026223.ui.theme.screens.RestaurantListScreen
import com.pdmtaller2.JuanCastellanos_00026223.ui.theme.screens.RestaurantDetailScreen



import com.pdmtaller2.JuanCastellanos_00026223.ui.screens.SearchScreen
import com.pdmtaller2.JuanCastellanos_00026223.ui.screens.OrdersScreen
import com.pdmtaller2.JuanCastellanos_00026223.ui.theme.ComidaRapidaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComidaRapidaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val restaurants = restaurantsinfo
                    AppNavigation(restaurants = restaurants)
                }
            }
        }
    }
}


sealed class Screen(val route: String) {
    object List : Screen("list")
    object Search : Screen("search")
    object Orders : Screen("orders")
    object Detail : Screen("detail/{restaurantId}") {
        fun createRoute(restaurantId: Int) = "detail/$restaurantId"
    }
}


@Composable
fun AppNavigation(restaurants: List<Restaurant>) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {

            val currentRoute = currentRoute(navController)
            if (currentRoute in listOf(Screen.List.route, Screen.Search.route, Screen.Orders.route)) {
                BottomNavBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.List.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Screen.List.route) {
                RestaurantListScreen(
                    restaurants = restaurants,
                    onItemClick = { id ->
                        navController.navigate(Screen.Detail.createRoute(id))
                    },
                    onSearchClick = {
                        navController.navigate(Screen.Search.route)
                    }
                )
            }


            composable(Screen.Search.route) {
                SearchScreen(
                    restaurants = restaurants,
                    onBackClick = { navController.popBackStack() },
                    onItemClick = { id ->
                        navController.navigate(Screen.Detail.createRoute(id))
                    }
                )
            }


            composable(Screen.Orders.route) {
                OrdersScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }


            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("restaurantId") { type = NavType.IntType }
                ) { backStackEntry ->
                    val restaurantId = backStackEntry.arguments?.getInt("restaurantId") ?: 0
                    val restaurant = restaurants.find { it.id == restaurantId }

                    RestaurantDetailScreen(
                        restaurant = restaurant,
                        onBackClick = { navController.popBackStack() },
                        onOrderClick = {

                            navController.navigate(Screen.Orders.route) {
                                popUpTo(navController.graph.findStartDestination().id)
                            }
                        }
                    )
                }
        }
    }
}


@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem(
            route = Screen.List.route,
            icon = Icons.Default.Home,
            label = "Listado"
        ),
        BottomNavItem(
            route = Screen.Search.route,
            icon = Icons.Default.Search,
            label = "Búsqueda"
        ),
        BottomNavItem(
            route = Screen.Orders.route,
            icon = Icons.Default.ShoppingCart,
            label = "Mis Órdenes"
        )
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = { Text(text = item.label) }
            )
        }
    }
}

data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
)


@Composable
private fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}