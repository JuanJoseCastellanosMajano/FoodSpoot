package com.pdmtaller2.JuanCastellanos_00026223.data

data class Dish(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String
)

data class Restaurant(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val categories: List<String>,
    val menu: List<Dish>
)