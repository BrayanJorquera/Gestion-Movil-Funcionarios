package com.duoc.gestion_movil_funcionarios.viewmodel

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
}

    data class Detail(val itemId: String) : Screen("detail/{itemId}"){
        fun buildRoute():String{
            return route.replace("{itemId}",itemId)
        }
    }
}