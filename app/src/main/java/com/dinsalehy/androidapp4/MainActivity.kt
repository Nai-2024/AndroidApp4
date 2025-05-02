package com.dinsalehy.androidapp4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dinsalehy.androidapp4.ui.theme.AndroidApp4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidApp4Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()  // Create NavController

                    // Set up the NavHost with a starting destination and routes
                    NavHost(navController = navController, startDestination = "landing_screen") {
                        composable("landing_screen") {
                            LandingScreen(onStartClick = {
                                // Navigate to location screen when the button is clicked
                                navController.navigate("location_list_screen")
                            })
                        }
                        composable("location_list_screen") {
                            LocationListScreen()
                        }
                    }
                }
            }
        }
    }
}


