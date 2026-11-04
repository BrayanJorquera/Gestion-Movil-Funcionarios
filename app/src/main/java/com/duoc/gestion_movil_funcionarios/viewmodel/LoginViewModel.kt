package com.duoc.gestion_movil_funcionarios.viewmodel
import androidx.lifecycle.ViewModel
import com.duoc.gestion_movil_funcionarios.model.LoginErrors
import com.duoc.gestion_movil_funcionarios.model.LoginUiState
import com.duoc.gestion_movil_funcionarios.repository.validatePassword
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
                estadoActual.correo.isBlank() -> "Error: El correo no puede estar vacio"
                !estadoActual.correo.contains("@") -> "Error: El correo no es valido"
                !estadoActual.correo.contains("@salud.cmq.cl") -> "Error: Solo se aceptan correo del dominio salud.cmq.cl"
                !UsuarioRepository.usuarioExiste(estadoActual.correo) -> "Error: Usuario no registrado"
                !UsuarioRepository.estaEnTurno(estadoActual.correo) -> "Acceso denegado: Se encuentra fuera de su jornada laboral"
                else -> null
            },
            contrasena = when{
                estadoActual.contrasena.isBlank() -> "Error: La contraseña no puede estar vacia"
                !validatePassword(estadoActual.correo, estadoActual.contrasena) -> "Error: usuario y/o contraseña incorrectos"
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