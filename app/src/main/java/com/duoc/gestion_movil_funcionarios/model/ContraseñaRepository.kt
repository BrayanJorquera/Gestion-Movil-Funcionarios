package com.duoc.gestion_movil_funcionarios.model

import org.duoc.hashPasword

private val hashList: Map<String, String> = mapOf(  "Funcionario_M@salud.cmq.cl" to """5pIxNJqZML]6s""",
                                                    "Funcionario_T@salud.cmq.cl" to """*k@;DEOV~y\R""",
                                                    "Administrativo_M@salud.cmq.cl" to """B>lAInYubSNQUVq""",
                                                    "Administrativo_T@salud.cmq.cl" to """aW:f?_PJIZRdXE""",
                                                    "Jefatura@salud.cmq.cl" to """jdC7rPEi""",
                                                    "RRHH@salud.cmq.cl" to """`NCr""",
                                                    "Administrador@salud.cmq.cl" to """NUSJkadyV6L@^""",
                                                    "AreaTIC@salud.cmq.cl" to  """mwWhM.n""",
                                                    "Supervisor@salud.cmq.cl" to """Ec`>~Lksl""")

fun validatePassword(correo:String,contrasena:String): Boolean {
    if(hashList[correo] == hashPasword(correo, contrasena)){
        return true
    } else {
        return false
    }
}