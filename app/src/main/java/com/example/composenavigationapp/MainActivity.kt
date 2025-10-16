package com.example.composenavigationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import com.example.composenavigationapp.ui.theme.ComposeNavigationAppTheme
import androidx.navigation.compose.rememberNavController
import com.example.composenavigationapp.ui.navigation.RootNavGraph
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composenavigationapp.ui.data.ThemePreference
import com.example.composenavigationapp.ui.viewmodel.SettingsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val settingsViewModel: SettingsViewModel = viewModel()
            val themePref = settingsViewModel.themePreference.collectAsStateWithLifecycle()

            val isDark = when (themePref.value) {
                ThemePreference.DARK -> true
                ThemePreference.LIGHT -> false
                ThemePreference.SYSTEM -> null // follow system
            }

            ComposeNavigationAppTheme(darkTheme = isDark ?: androidx.compose.foundation.isSystemInDarkTheme()) {
                val navController = rememberNavController()
                RootNavGraph(navController = navController)
            }
        }
    }
}

