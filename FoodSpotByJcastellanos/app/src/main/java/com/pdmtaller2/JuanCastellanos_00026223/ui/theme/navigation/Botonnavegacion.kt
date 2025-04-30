package com.pdmtaller2.JuanCastellanos_00026223.ui.theme.navigation

sealed class BottomNavItem(val route: String, val label: String) {
    object Listado : BottomNavItem("listado", "Listado")
    object Busqueda : BottomNavItem("busqueda", "Búsqueda")
    object Ordenes : BottomNavItem("ordenes", "Mis órdenes")
}
