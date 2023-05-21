package com.example.gotapp.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gotapp.ui.details.Details
import com.example.gotapp.ui.main.Main

@Composable
fun Navigation (modifier: Modifier) {
    val navController = LocalNavController.current
    NavHost(navController = navController, startDestination = "main_page", modifier = modifier) {
        composable("main_page") {
            Main()
        }
        composable("details_page/{slug}/{title}",  arguments = listOf(
            navArgument("slug") { type = NavType.StringType },
            navArgument("title") { type = NavType.StringType }
        )) { backStackEntry ->
            Details(
               slug = backStackEntry.arguments?.getString("slug"),
               title =  backStackEntry.arguments?.getString("title")
            )
        }
    }
}