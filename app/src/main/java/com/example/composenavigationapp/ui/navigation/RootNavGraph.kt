package com.example.composenavigationapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import androidx.navigation.compose.composable
import com.example.composenavigationapp.ui.screens.PlaceholderHomeScreen
import com.example.composenavigationapp.ui.screens.SplashScreen

@Composable
fun RootNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {
        // 1) Splash sebagai start
        composable(Routes.SPLASH) {
            SplashScreen(
                onFinished = {
                    // Pindah ke main_graph dan hapus splash dari back stack
                    navController.navigate(Routes.MAIN_GRAPH) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        // Ganti: sebelumnya 'navigation(route = MAIN_GRAPH) { ... }'
        // Sekarang: satu composable yang menampilkan MainScaffold
        composable(Routes.MAIN_GRAPH) {
            MainScaffold() // kita buat di langkah 3.2
        }
    }
}