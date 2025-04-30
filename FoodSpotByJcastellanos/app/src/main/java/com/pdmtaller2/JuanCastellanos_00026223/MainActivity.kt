package com.pdmtaller2.JuanCastellanos_00026223

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.pdmtaller2.JuanCastellanos_00026223.data.restaurantsinfo
import com.pdmtaller2.JuanCastellanos_00026223.ui.theme.navigation.AppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface {
                    val restaurants = restaurantsinfo
                    AppNavHost(restaurants = restaurants)
                }
            }
        }
    }
}
