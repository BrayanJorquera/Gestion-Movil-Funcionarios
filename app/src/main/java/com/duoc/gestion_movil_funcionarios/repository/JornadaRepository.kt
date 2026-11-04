package com.duoc.gestion_movil_funcionarios.repository
import com.duoc.gestion_movil_funcionarios.model.HorarioSemanal
import com.duoc.gestion_movil_funcionarios.model.Jornada
import java.time.DayOfWeek
import java.time.LocalTime

object JornadaRepository {
    private val horaIniM: LocalTime = LocalTime.of(7,0)
    private val horaTerM: LocalTime = LocalTime.of(15,0)
    private val horaIniT: LocalTime = LocalTime.of(12,0)
    private val horaTerT: LocalTime = LocalTime.of(20,0)

    private val horarioManana = HorarioSemanal(turnos = mapOf(  DayOfWeek.MONDAY to Jornada.Turno(horaIniM,horaTerM),
                                                                DayOfWeek.TUESDAY to Jornada.Turno(horaIniM,horaTerM),
                                                                DayOfWeek.WEDNESDAY to Jornada.Turno(horaIniM,horaTerM),
                                                                DayOfWeek.THURSDAY to Jornada.Turno(horaIniM,horaTerM),
                                                                DayOfWeek.FRIDAY to Jornada.Turno(horaIniM,horaTerM),
                                                                DayOfWeek.SATURDAY to Jornada.Turno(horaIniM,horaTerM),
                                                                DayOfWeek.SUNDAY to Jornada.Libre))

    private val horarioTarde = HorarioSemanal(turnos = mapOf(   DayOfWeek.MONDAY to Jornada.Turno(horaIniT,horaTerT),
                                                                DayOfWeek.TUESDAY to Jornada.Turno(horaIniT,horaTerT),
                                                                DayOfWeek.WEDNESDAY to Jornada.Turno(horaIniT,horaTerT),
                                                                DayOfWeek.THURSDAY to Jornada.Turno(horaIniT,horaTerT),
                                                                DayOfWeek.FRIDAY to Jornada.Turno(horaIniT,horaTerT),
                                                                DayOfWeek.SATURDAY to Jornada.Turno(horaIniT,horaTerT),
                                                                DayOfWeek.SUNDAY to Jornada.Libre))
    fun turnosM(): HorarioSemanal{ return horarioManana }
    fun turnosT(): HorarioSemanal{ return horarioTarde }
}