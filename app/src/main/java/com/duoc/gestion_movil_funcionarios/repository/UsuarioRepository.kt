package com.duoc.gestion_movil_funcionarios.repository
import com.duoc.gestion_movil_funcionarios.model.Rol
import com.duoc.gestion_movil_funcionarios.model.Usuario

class UsuarioRepository {
    private val jornadaRepository: JornadaRepository = JornadaRepository()
    private val turnosM = jornadaRepository.turnosM()
    private val turnosT = jornadaRepository.turnosT()
    private var usuarios = mutableListOf(  Usuario(11111111,"1","Funcionario Mañana",
                                                            "Funcionario_M@med.cl",
                                                            Rol.FuncionarioSalud, turnosM),
                                                    Usuario(22222222,"2","Funcionario Tarde",
                                                            "Funcionario_T@med.cl",
                                                            Rol.FuncionarioSalud, turnosT),
                                                    Usuario(33333333,"3","Administrativo Mañana",
                                                            "Administrativo_M@med.cl",
                                                            Rol.PersonalAdministrativo, turnosM),
                                                    Usuario(44444444,"4","Administrativo Tarde",
                                                            "Administrativo_T@med.cl",
                                                            Rol.PersonalAdministrativo, turnosT),
                                                    Usuario(55555555,"5","Jefatura",
                                                            "Jefatura@med.cl",
                                                            Rol.JefaturaCentro, turnosM),
                                                    Usuario(66666666,"6","Recursos Humanos",
                                                            "RRHH@med.cl",
                                                            Rol.RecursosHumanos, turnosM),
                                                    Usuario(77777777,"7","Administrador Sistema",
                                                            "Administrador@med.cl",
                                                            Rol.AdministradorSistema, turnosM),
                                                    Usuario(88888888,"8","Area TIC",
                                                            "AreaTIC@med.cl",
                                                            Rol.AreaTICiberseguridad,turnosM),
                                                    Usuario(99999999,"9","Supervisor Institucional",
                                                            "Supervisor@med.cl",
                                                            Rol.SupervisorInstitucional,turnosM))
}