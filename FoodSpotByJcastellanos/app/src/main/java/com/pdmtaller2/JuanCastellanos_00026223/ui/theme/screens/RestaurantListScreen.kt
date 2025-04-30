package com.pdmtaller2.JuanCastellanos_00026223.ui.theme.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

import com.pdmtaller2.JuanCastellanos_00026223.data.Restaurant


@Composable
fun RestaurantListScreen(
    restaurants: List<Restaurant>,
    onRestaurantClick: (Int) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(restaurants) { restaurant ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onRestaurantClick(restaurant.id) }
            ) {
                Row(modifier = Modifier.padding(8.dp)) {
                    Image(
                        painter = rememberAsyncImagePainter(restaurant.imageUrl),
                        contentDescription = restaurant.name,
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(text = restaurant.name, style = MaterialTheme.typography.titleMedium)
                        Text(text = restaurant.description, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}


@Composable
fun RestaurantListScreen() {
    val sections = listOf(
        RestaurantSection("Comida Rápida", listOf(/* 2 restaurantes */)),
        RestaurantSection("Postres", listOf(/* 2 restaurantes */)),
        RestaurantSection("Comida Mexicana", listOf(/* 2 restaurantes */)),
        RestaurantSection("Sushi", listOf(/* 2 restaurantes */)),
        RestaurantSection("Italiana", listOf(/* 2 restaurantes */)),
        RestaurantSection("Parrilla", listOf(/* 2 restaurantes */)),
        RestaurantSection("Vegetariana", listOf(/* 2 restaurantes */))
    )

    LazyColumn {
        items(sections) { section ->
            Text(
                text = section.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(section.restaurants) { restaurant ->
                    RestaurantCard(restaurant)
                }
            }
        }
    }
}
