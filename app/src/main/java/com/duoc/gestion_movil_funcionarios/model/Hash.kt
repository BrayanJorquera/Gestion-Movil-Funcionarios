package org.duoc
import kotlin.random.Random

//Simulacion de Hash
fun hashPasword(correo:String, contrasena:String):String{
    val rng = Random(salt(correo)) //Simula el salt, haciento que para cada usuario la opecaion varie segun los caracteres de su correo
    var hashed = mutableSetOf<Char>()
    for (char in contrasena){
        val ran = rng.nextInt(-47, 48) //De -47 a 47. Valor elegido por ser el centro del rango imrpimible (32..126)
        hashed.add(hashChar(char,ran))
    }
    return hashed.joinToString("")
}

//Altera cada caracter por el numero aleatorio
fun hashChar(char: Char, ran: Int): Char{
    var hashed = char.code + ran
    if (hashed!=36){//Previene que aparezca el operador '$'
        if(hashed in 32..126){
            return hashed.toChar()
        } else { //Si se sale del rango imprimible se le aplica la operacion contraria.
            return (char.code - ran).toChar()
        }
    } else {return 'S'}
}

//Obteiene el valor que sera usado en la seed
fun salt(correo:String): Int {
    var NaCl = 0
    for (char in correo.lowercase()){ //Lowercase para permitir al usuario escribir su correo como guste
        NaCl += char.code //Suma al assci code de cada caracter del correo, haciendo que incluso con una misma contraseña todos tengan hashes distintos.
    }
    return NaCl
}