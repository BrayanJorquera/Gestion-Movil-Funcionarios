package com.duoc.gestion_movil_funcionarios
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.duoc.gestion_movil_funcionarios.ui.screens.HomeScreen
import com.duoc.gestion_movil_funcionarios.ui.screens.LoginScreen
import com.duoc.gestion_movil_funcionarios.ui.screens.ProfileScreen
import com.duoc.gestion_movil_funcionarios.ui.screens.SettingsScreen
import com.duoc.gestion_movil_funcionarios.ui.theme.GestionMovilFuncionariosTheme
import com.duoc.gestion_movil_funcionarios.viewmodel.LoginViewModel
import com.duoc.gestion_movil_funcionarios.viewmodel.MainViewModel
import com.duoc.gestion_movil_funcionarios.viewmodel.NavigationEvent
import com.duoc.gestion_movil_funcionarios.viewmodel.Screen
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GestionMovilFuncionariosTheme {
                val viewModel: MainViewModel = viewModel()
                val loginViewModel: LoginViewModel = viewModel()
                val navController = rememberNavController()

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
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ){innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ){
                        composable(Screen.Home.route){HomeScreen(navController,viewModel) }
                        composable(Screen.Profile.route){ProfileScreen(navController,viewModel) }
                        composable(Screen.Settings.route){SettingsScreen(navController,viewModel)}
                        composable(Screen.Login.route){LoginScreen(navController,loginViewModel)}
                    }
                }
            }
        }
    }
}