package com.pdmtaller2.JuanCastellanos_00026223.ui.theme.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        NavItem.Listado,
        NavItem.Busqueda,
        NavItem.MisOrdenes
    )

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Evita múltiples copias de la misma pantalla
                        launchSingleTop = true
                        // Restaura el estado cuando se vuelve a seleccionar
                        restoreState = true
                    }
                }
            )
        }
    }
}

sealed class NavItem(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Listado : NavItem("listado", "Listado", R.drawable.ic_list)
    object Busqueda : NavItem("busqueda", "Búsqueda", R.drawable.ic_search)
    object MisOrdenes : NavItem("ordenes", "Mis órdenes", R.drawable.ic_orders)
}