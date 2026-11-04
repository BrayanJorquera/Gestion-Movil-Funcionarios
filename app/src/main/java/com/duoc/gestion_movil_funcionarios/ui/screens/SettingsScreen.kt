package com.duoc.gestion_movil_funcionarios.ui.screens
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.duoc.gestion_movil_funcionarios.viewmodel.MainViewModel
import com.duoc.gestion_movil_funcionarios.viewmodel.Screen

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel(),
    onOpenDrawer: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Pantalla de Configuración (Settings)")
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                viewModel.navigateTo(Screen.Home)
            }
        ) {
            Text(text = "Volver al Inicio")
        }
    }
}