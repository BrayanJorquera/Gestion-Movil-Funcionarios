package com.duoc.gestion_movil_funcionarios.ui.screens
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.duoc.gestion_movil_funcionarios.LogedUser
import com.duoc.gestion_movil_funcionarios.model.Rol
import com.duoc.gestion_movil_funcionarios.viewmodel.MainViewModel
import com.duoc.gestion_movil_funcionarios.viewmodel.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel(),
    onOpenDrawer: () -> Unit
) {
    val items = listOf(Screen.Home, Screen.Profile)
    var selectedItem by remember { mutableStateOf(1) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") },
                navigationIcon = {
                    IconButton(onClick = onOpenDrawer){
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                }
            )
        },

        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            viewModel.navigateTo(screen)
                        },
                        label = { Text(text = screen.route) },
                        icon = {
                            Icon(
                                imageVector = if (screen == Screen.Home) Icons.Default.Home else Icons.Default.Person,
                                contentDescription = screen.route
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if(LogedUser.logged){
                Text(text = "Perfil Usuario.")
                Text("")
                Text("Nombre: "+LogedUser.nombre)
                Text("Run: "+ LogedUser.numrun+"-"+ LogedUser.dvrun)
                Text("Correo: "+LogedUser.correo)
                Text("Rol: "+when(LogedUser.rol){
                    Rol.FuncionarioSalud -> "Funcionario del área de salud"
                    Rol.PersonalAdministrativo -> "Personal Administrativo"
                    Rol.JefaturaCentro -> "Jefatura del centro"
                    Rol.RecursosHumanos -> "Área de recursos humanos"
                    Rol.AdministradorSistema -> "Administrador del sistema"
                    Rol.AreaTICiberseguridad -> "Area TI y cyberseguridad"
                    Rol.SupervisorInstitucional -> "Supervisor Institucional"
                    else -> "null"
                })
            }else{
                Text("Debes iniciar seccion para ver tus datos.")
            }

        }
    }
}