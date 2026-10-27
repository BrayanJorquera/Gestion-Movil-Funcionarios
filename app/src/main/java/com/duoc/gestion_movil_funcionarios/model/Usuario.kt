package com.duoc.gestion_movil_funcionarios.model

class Usuario  (val numrun: Int,
                val dvrun: String,
                val nombre:String,
                val rol: Rol,
                val turnos: HorarioSemanal) {
}
