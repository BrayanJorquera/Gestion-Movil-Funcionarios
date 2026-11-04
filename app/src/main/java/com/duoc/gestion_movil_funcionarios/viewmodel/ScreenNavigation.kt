package com.duoc.gestion_movil_funcionarios.viewmodel

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Settings : Screen("settings")

    object Funcionario:Screen("funcionario")
    object Administrativo:Screen("administrativo")
    object Jefatura:Screen("jefatura")
    object RrHh:Screen("rrhh")
    object Admin:Screen("admin")
    object Tecnologias:Screen("tecnologias")
    object Supervistor:Screen("supervistor")
}

    data class Detail(val itemId: String) : Screen("detail/{itemId}"){
        fun buildRoute():String{
            return route.replace("{itemId}",itemId)
        }
    }
}