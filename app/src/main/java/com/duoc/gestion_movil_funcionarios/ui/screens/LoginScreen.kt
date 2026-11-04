package com.duoc.gestion_movil_funcionarios.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.duoc.gestion_movil_funcionarios.LogedUser
import com.duoc.gestion_movil_funcionarios.repository.UsuarioRepository
import com.duoc.gestion_movil_funcionarios.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel
){
    val estado by viewModel.estado.collectAsState()
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ){
        OutlinedTextField(
            value = estado.correo,
            onValueChange = viewModel::onCorreoChange,
            label = { Text("Correo Electrónico") },
            isError = estado.errores.correo != null,
            supportingText = {
                estado.errores.correo?.let {
                    Text(it,color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = estado.contrasena,
            onValueChange = viewModel::onContrasenaChange,
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            isError = estado.errores.contrasena != null,
            supportingText = {
                estado.errores.contrasena?.let {
                    Text(it,color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                var usuario = UsuarioRepository.obtenerUsuarioPorCorreo(estado.correo)
                if(viewModel.validarLogin()){
                    LogedUser.logged = true
                    LogedUser.numrun = usuario!!.numrun
                    LogedUser.dvrun = usuario.dvrun
                    LogedUser.nombre = usuario.nombre
                    LogedUser.correo = usuario.correo
                    LogedUser.rol = usuario.rol
                    navController.navigate("profile")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Iniciar Sesión")
        }
    }
}