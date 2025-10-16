package com.example.composenavigationapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composenavigationapp.ui.data.ThemePreference
import com.example.composenavigationapp.ui.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(vm: SettingsViewModel = viewModel()) {
    val selectedTheme by vm.themePreference.collectAsStateWithLifecycle()
    val notificationsEnabled by vm.notificationsEnabled.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Pengaturan", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        Text("Tema aplikasi", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        ThemeOptionRow(
            title = "Ikuti sistem",
            selected = selectedTheme == ThemePreference.SYSTEM,
            onClick = { vm.updateThemePreference(ThemePreference.SYSTEM) }
        )
        ThemeOptionRow(
            title = "Terang",
            selected = selectedTheme == ThemePreference.LIGHT,
            onClick = { vm.updateThemePreference(ThemePreference.LIGHT) }
        )
        ThemeOptionRow(
            title = "Gelap",
            selected = selectedTheme == ThemePreference.DARK,
            onClick = { vm.updateThemePreference(ThemePreference.DARK) }
        )

        Spacer(Modifier.height(16.dp))
        Divider()
        Spacer(Modifier.height(16.dp))

        Text("Preferensi notifikasi", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        ListItem(
            headlineContent = { Text("Aktifkan notifikasi") },
            trailingContent = {
                Switch(
                    checked = notificationsEnabled,
                    onCheckedChange = { vm.updateNotificationsEnabled(it) }
                )
            }
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = if (notificationsEnabled) "Notifikasi diaktifkan" else "Notifikasi dinonaktifkan",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ThemeOptionRow(title: String, selected: Boolean, onClick: () -> Unit) {
    ListItem(
        modifier = Modifier.fillMaxWidth(),
        headlineContent = { Text(title) },
        trailingContent = {
            RadioButton(selected = selected, onClick = onClick)
        },
        overlineContent = null,
        supportingContent = null,
        leadingContent = null
    )
}