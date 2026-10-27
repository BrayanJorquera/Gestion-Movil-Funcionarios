package com.duoc.gestion_movil_funcionarios.model
import java.time.LocalTime
import java.time.DayOfWeek

/*Simulacion simplificada de horarios de trabajo para el MVP.
  En un entorno real se utilizaria el registro diginal del horario de los trabajadores con
  su horario de marcacion de inicio y final de turno.*/
sealed class Jornada{
    data class Mañana(val horaIni: LocalTime = LocalTime.of(7,0),
                      val horaTer: LocalTime = LocalTime.of(15,0)) : Jornada()
    data class Tarde(val horaIni: LocalTime = LocalTime.of(13,0),
                     val horaTer: LocalTime = LocalTime.of(23,59)) : Jornada()
    object Libre : Jornada()
}

data class HorarioSemanal(
    val turnos: Map<DayOfWeek, Jornada>
)