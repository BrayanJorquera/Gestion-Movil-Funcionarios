package com.duoc.gestion_movil_funcionarios.model

data class LoginUiState(
    val correo: String = "",
    val contrasena: String = "",
    //val errorCounter: Int = 0,
    val errores: LoginErrors = LoginErrors()
)