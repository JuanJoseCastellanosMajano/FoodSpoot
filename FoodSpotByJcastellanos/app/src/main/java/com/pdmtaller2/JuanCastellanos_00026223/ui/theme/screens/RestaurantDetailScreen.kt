package com.pdmtaller2.JuanCastellanos_00026223.ui.theme.screens


import androidx.compose.foundation.Image
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
fun RestaurantDetailScreen(restaurant: Restaurant) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = restaurant.name, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = rememberAsyncImagePainter(restaurant.imageUrl),
            contentDescription = restaurant.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = restaurant.description)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Menú", style = MaterialTheme.typography.titleLarge)
        LazyColumn {
            items(restaurant.menu) { dish ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(modifier = Modifier.padding(8.dp)) {
                        Image(
                            painter = rememberAsyncImagePainter(dish.imageUrl),
                            contentDescription = dish.name,
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(text = dish.name, style = MaterialTheme.typography.titleSmall)
                            Text(text = dish.description, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}
