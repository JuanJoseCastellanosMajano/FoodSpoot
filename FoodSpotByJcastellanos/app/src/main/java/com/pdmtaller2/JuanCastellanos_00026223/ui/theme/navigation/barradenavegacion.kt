package com.pdmtaller2.JuanCastellanos_00026223.ui.theme.navigation

import androidx.compose.material3.BottomNavigation
import androidx.compose.material3.BottomNavigationItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Listado,
        BottomNavItem.Busqueda,
        BottomNavItem.Ordenes
    )

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = { Text(item.label) },
                icon = {} // Puedes agregar íconos si quieres
            )
        }
    }
}
