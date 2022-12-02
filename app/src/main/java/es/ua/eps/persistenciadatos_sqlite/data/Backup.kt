package es.ua.eps.persistenciadatos_sqlite.data

import java.io.File
import java.lang.Exception

data class Backup(
    val fecha:String="",
    val hora:String="",
    val tipo:String="",
    val numeroUsuarios:String="",
    val file:File
) {

}

fun checkNameCanBeBackup(input:File):Backup?{
    try{
        val parts=input.name.split("_")
        return Backup(parts[0],parts[1],parts[2],parts[3],input)
    } catch (e:Exception){}
    return null
}