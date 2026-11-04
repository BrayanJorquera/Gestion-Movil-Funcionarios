package com.duoc.gestion_movil_funcionarios.viewmodel

import androidx.lifecycle.ViewModel
import com.duoc.gestion_movil_funcionarios.model.LoginErrors
import com.duoc.gestion_movil_funcionarios.model.LoginUiState
import com.duoc.gestion_movil_funcionarios.model.validatePassword
import com.duoc.gestion_movil_funcionarios.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel: ViewModel() {
    private val _estado = MutableStateFlow(LoginUiState())
    val estado: StateFlow<LoginUiState> = _estado

    fun onCorreoChange(valor: String){
        _estado.update { it.copy(correo = valor,errores=it.errores.copy(correo=null)) }
    }
    fun onContrasenaChange(valor: String){
        _estado.update { it.copy(contrasena = valor,errores=it.errores.copy(contrasena=null)) }
    }

    //Validacion global del login
    fun validarLogin():Boolean{
        val estadoActual = _estado.value
        val errores = LoginErrors(
            correo = when{
                estadoActual.correo.isBlank() -> "El correo no puede estar vacio"
                !estadoActual.correo.contains("@") -> "El correo no es valido"
                !estadoActual.correo.contains("@salud.cmq.cl") -> "Solo se aceptan correo del dominio salud.cmq.cl"
                !UsuarioRepository.usuarioExiste(estadoActual.correo) -> "Usuario no registrado"
                else -> null
            },
            contrasena = when{
                estadoActual.contrasena.isBlank() -> "La contraseña no puede estar vacia"
                !validatePassword(estadoActual.correo, estadoActual.contrasena) -> "Usuario y/o Contraseña incorrectos"
                else -> null
            }
        )

        val hayErrores = listOfNotNull(
            errores.correo,
            errores.contrasena
        ).isNotEmpty()

        _estado.update { it.copy(errores = errores) }
        return !hayErrores
    }
}