package com.pdmtaller2.JuanCastellanos_00026223.ui.theme.screens

package com.pdmtaller2.JuanCastellanos_00026223.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.pdmtaller2.JuanCastellanos_00026223.data.Restaurant
import com.pdmtaller2.JuanCastellanos_00026223.data.restaurantsinfo

@Composable
fun SearchScreen(
    onBack: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    val allRestaurants = remember { restaurantsinfo }

    val filteredRestaurants = remember(searchText.text) {
        if (searchText.text.isEmpty()) {
            allRestaurants
        } else {
            allRestaurants.filter { restaurant ->
                restaurant.name.contains(searchText.text, ignoreCase = true) ||
                        restaurant.description.contains(searchText.text, ignoreCase = true) ||
                        restaurant.menu.any { dish ->
                            dish.name.contains(searchText.text, ignoreCase = true) ||
                                    dish.description.contains(searchText.text, ignoreCase = true)
                        }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver"
                )
            }

            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                placeholder = { Text("Buscar restaurantes o platillos...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar"
                    )
                },
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        if (filteredRestaurants.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (searchText.text.isEmpty())
                        "Busca restaurantes o platillos"
                    else
                        "No se encontraron resultados",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredRestaurants) { restaurant ->
                    RestaurantSearchItem(
                        restaurant = restaurant,
                        onClick = { onItemClick(restaurant.id) }
                    )
                    Divider()
                }
            }
        }
    }
}

@Composable
fun RestaurantSearchItem(
    restaurant: Restaurant,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = restaurant.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = restaurant.description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Platillos: ${restaurant.menu.joinToString { it.name }}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}