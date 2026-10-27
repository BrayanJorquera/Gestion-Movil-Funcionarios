package com.duoc.gestion_movil_funcionarios.model

sealed class Rol {
    object FuncionarioSalud : Rol()
    object PersonalAdministrativo : Rol()
    object JefaturaCentro : Rol()
    object RecursosHumanos : Rol()
    object AdministradorSistema : Rol()
    object AreaTICiberseguridad : Rol()
    object SupervisorInstitucional : Rol()
}