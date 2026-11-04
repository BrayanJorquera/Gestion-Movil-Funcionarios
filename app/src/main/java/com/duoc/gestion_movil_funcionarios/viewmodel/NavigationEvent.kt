package com.duoc.gestion_movil_funcionarios.viewmodel

sealed class NavigationEvent{
    data class NavigateTo(  val route: Screen,
                            val popUpToRoute: Screen? = null,
                            val inclusive: Boolean = false,
                            val singleTop: Boolean = false): NavigationEvent()
    object PopBackStack: NavigationEvent()
    object NavigateUp: NavigationEvent()
}