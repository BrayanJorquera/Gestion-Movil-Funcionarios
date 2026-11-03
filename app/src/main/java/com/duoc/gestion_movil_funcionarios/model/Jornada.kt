package com.duoc.gestion_movil_funcionarios.model
import java.time.LocalTime
import java.time.DayOfWeek

/*Simulacion simplificada de horarios de trabajo para el MVP.
  En un entorno real se utilizaria el registro diginal del horario de los trabajadores con
  su horario de marcacion de inicio y final de turno.*/
sealed class Jornada{
    data class Turno(val horaIni: LocalTime,
                      val horaTer: LocalTime) : Jornada()
    object Libre : Jornada()
}

data class HorarioSemanal(
    val turnos: Map<DayOfWeek, Jornada>
)