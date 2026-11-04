package com.duoc.gestion_movil_funcionarios
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.duoc.gestion_movil_funcionarios.model.Rol
import com.duoc.gestion_movil_funcionarios.ui.screens.AdminScreen
import com.duoc.gestion_movil_funcionarios.ui.screens.AdministrativoScreen
import com.duoc.gestion_movil_funcionarios.ui.screens.FuncionarioScreen
import com.duoc.gestion_movil_funcionarios.ui.screens.HomeScreen
import com.duoc.gestion_movil_funcionarios.ui.screens.JefaturaScreen
import com.duoc.gestion_movil_funcionarios.ui.screens.LoginScreen
import com.duoc.gestion_movil_funcionarios.ui.screens.ProfileScreen
import com.duoc.gestion_movil_funcionarios.ui.screens.RrHhScreen
import com.duoc.gestion_movil_funcionarios.ui.screens.SettingsScreen
import com.duoc.gestion_movil_funcionarios.ui.screens.SupervisorScreen
import com.duoc.gestion_movil_funcionarios.ui.screens.TecnologiasScreen
import com.duoc.gestion_movil_funcionarios.ui.theme.GestionMovilFuncionariosTheme
import com.duoc.gestion_movil_funcionarios.viewmodel.LoginViewModel
import com.duoc.gestion_movil_funcionarios.viewmodel.MainViewModel
import com.duoc.gestion_movil_funcionarios.viewmodel.NavigationEvent
import com.duoc.gestion_movil_funcionarios.viewmodel.Screen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GestionMovilFuncionariosTheme {
                val viewModel: MainViewModel = viewModel()
                val loginViewModel: LoginViewModel = viewModel()
                val navController = rememberNavController()

                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                LaunchedEffect(key1 = Unit){
                    viewModel.navigationEvents.collectLatest { event ->
                        when(event){
                            is NavigationEvent.NavigateTo -> {
                                navController.navigate(event.route.route){
                                    event.popUpToRoute?.let{
                                        popUpTo(it.route){
                                            inclusive = event.inclusive
                                        }
                                    }
                                    launchSingleTop = event.singleTop
                                    restoreState = true
                                }
                            }
                            is NavigationEvent.PopBackStack -> navController.popBackStack()
                            is NavigationEvent.NavigateUp -> navController.navigateUp()
                        }
                    }
                }

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            Text("Menú",modifier = Modifier.padding(16.dp))
                            NavigationDrawerItem(
                                label = { Text("Perfil") },
                                selected = false,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    viewModel.navigateTo(Screen.Profile)
                                }
                            )
                            NavigationDrawerItem(
                                label = { Text("Configuracion") },
                                selected = false,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    viewModel.navigateTo(Screen.Settings)
                                }
                            )
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 8.dp),
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            NavigationDrawerItem(
                                label = { Text("Funcionario de Salud") },
                                selected = false,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    viewModel.navigateTo(Screen.Funcionario)
                                }
                            )
                            NavigationDrawerItem(
                                label = { Text("Personal Administrativo") },
                                selected = false,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    viewModel.navigateTo(Screen.Administrativo)
                                }
                            )
                            NavigationDrawerItem(
                                label = { Text("Jefatura del Centro") },
                                selected = false,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    viewModel.navigateTo(Screen.Jefatura)
                                }
                            )
                            NavigationDrawerItem(
                                label = { Text("Recursos Humanos") },
                                selected = false,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    viewModel.navigateTo(Screen.RrHh)
                                }
                            )
                            NavigationDrawerItem(
                                label = { Text("Administrador del Sistema") },
                                selected = false,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    viewModel.navigateTo(Screen.Admin)
                                }
                            )
                            NavigationDrawerItem(
                                label = { Text("Area TI y ciberseguridad") },
                                selected = false,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    viewModel.navigateTo(Screen.Tecnologias)
                                }
                            )
                            NavigationDrawerItem(
                                label = { Text("Supervisor Institucional") },
                                selected = false,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    viewModel.navigateTo(Screen.Supervistor)
                                }
                            )
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 8.dp),
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            NavigationDrawerItem(
                                label = { Text("Cerrar Sesion") },
                                selected = false,
                                onClick = {
                                    LogedUser.logged = false
                                    LogedUser.rol = Rol.Null
                                    viewModel.navigateTo(Screen.Login)
                                }
                            )
                        }
                    }
                ){
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                    ){innerPadding ->
                        val openDrawer: () -> Unit = {
                            scope.launch { drawerState.open() }
                        }
                        NavHost(
                            navController = navController,
                            startDestination = Screen.Home.route,
                            modifier = Modifier.padding(innerPadding)
                        ){
                            composable(Screen.Home.route){HomeScreen(navController,viewModel,openDrawer) }
                            composable(Screen.Profile.route){ProfileScreen(navController,viewModel,openDrawer) }
                            composable(Screen.Settings.route){SettingsScreen(navController,viewModel,openDrawer)}
                            composable(Screen.Login.route){LoginScreen(navController,loginViewModel)}

                            composable(Screen.Funcionario.route){FuncionarioScreen(navController,viewModel,openDrawer)}
                            composable(Screen.Administrativo.route){AdministrativoScreen(navController,viewModel,openDrawer) }
                            composable(Screen.Jefatura.route){JefaturaScreen(navController,viewModel,openDrawer) }
                            composable(Screen.RrHh.route){RrHhScreen(navController,viewModel,openDrawer) }
                            composable(Screen.Admin.route){AdminScreen(navController,viewModel,openDrawer) }
                            composable(Screen.Tecnologias.route){TecnologiasScreen(navController,viewModel,openDrawer) }
                            composable(Screen.Supervistor.route){SupervisorScreen(navController,viewModel,openDrawer) }
                        }
                    }
                }
            }
        }
    }
}