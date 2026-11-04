package com.duoc.gestion_movil_funcionarios

import com.duoc.gestion_movil_funcionarios.model.Rol

object LogedUser{
    var logged: Boolean = false
    var numrun: Int = 0
    var dvrun: String = ""
    var nombre: String = ""
    var correo: String = ""
    var rol: Rol = Rol.Null
}