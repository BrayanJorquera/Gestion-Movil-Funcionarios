package com.duoc.gestion_movil_funcionarios.repository
import com.duoc.gestion_movil_funcionarios.model.Jornada
import com.duoc.gestion_movil_funcionarios.model.Rol
import com.duoc.gestion_movil_funcionarios.model.Usuario
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

object UsuarioRepository {
    private val turnosM = JornadaRepository.turnosM()
    private val turnosT = JornadaRepository.turnosT()
    private var usuarios = mutableListOf(  Usuario(11111111,"1","Funcionario Mañana",
                                                            "Funcionario_M@salud.cmq.cl",
                                                            Rol.FuncionarioSalud, turnosM),
                                                    Usuario(22222222,"2","Funcionario Tarde",
                                                            "Funcionario_T@salud.cmq.cl",
                                                            Rol.FuncionarioSalud, turnosT),
                                                    Usuario(33333333,"3","Administrativo Mañana",
                                                            "Administrativo_M@salud.cmq.cl",
                                                            Rol.PersonalAdministrativo, turnosM),
                                                    Usuario(44444444,"4","Administrativo Tarde",
                                                            "Administrativo_T@salud.cmq.cl",
                                                            Rol.PersonalAdministrativo, turnosT),
                                                    Usuario(55555555,"5","Jefatura",
                                                            "Jefatura@salud.cmq.cl",
                                                            Rol.JefaturaCentro, turnosM),
                                                    Usuario(66666666,"6","Recursos Humanos",
                                                            "RRHH@salud.cmq.cl",
                                                            Rol.RecursosHumanos, turnosM),
                                                    Usuario(77777777,"7","Administrador Sistema",
                                                            "Administrador@salud.cmq.cl",
                                                            Rol.AdministradorSistema, turnosM),
                                                    Usuario(88888888,"8","Area TIC",
                                                            "AreaTIC@salud.cmq.cl",
                                                            Rol.AreaTICiberseguridad,turnosM),
                                                    Usuario(99999999,"9","Supervisor Institucional",
                                                            "Supervisor@salud.cmq.cl",
                                                            Rol.SupervisorInstitucional,turnosM))

    //Verifica que el correo esté en la lista de usuarios
    fun usuarioExiste(correo: String): Boolean{
        return usuarios.any { it.correo.equals(correo, ignoreCase = true) }
    }

    //Obtiene un usuario por su correo
    fun obtenerUsuarioPorCorreo(correo: String): Usuario? {
        if(usuarioExiste(correo)){
            return usuarios.find { it.correo.equals(correo, ignoreCase = true) }
        }
        return null
    }

    //Verigica si el usuario está en turno
    fun estaEnTurno(correo: String,
                    diaActual: DayOfWeek = LocalDate.now().dayOfWeek,
                    horaActual: LocalTime = LocalTime.now()): Boolean {
        val usuario = obtenerUsuarioPorCorreo(correo) ?: return false //Obtiene el usuario
        val jornadaHoy = usuario.turnos.turnos[diaActual] ?: return false //Comprueba si traba hoy

        return when (jornadaHoy) { //Retorna verdadero si esta en turno
            is Jornada.Turno -> {
                // Maneja tanto rangos normales como turnos que crucen la medianoche
                if (jornadaHoy.horaTer.isBefore(jornadaHoy.horaIni)) {
                    horaActual >= jornadaHoy.horaIni || horaActual <= jornadaHoy.horaTer
                } else {
                    horaActual >= jornadaHoy.horaIni && horaActual <= jornadaHoy.horaTer
                }
            }
            is Jornada.Libre -> false
        }
    }
}