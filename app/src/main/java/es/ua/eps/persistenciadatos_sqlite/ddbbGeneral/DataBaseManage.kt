package es.ua.eps.persistenciadatos_sqlite.ddbbGeneral

import android.content.Context
import es.ua.eps.persistenciadatos_sqlite.data.Backup
import es.ua.eps.persistenciadatos_sqlite.data.User
import es.ua.eps.persistenciadatos_sqlite.data.checkNameCanBeBackup
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.util.*
import java.util.Calendar.*

class DataBaseManage(val context: Context,val type:DataBaseType) {

    // AUX FUNCTIONS
    private fun getNewFileName(users: List<User>):String{
        val cal:Calendar=getInstance()

        val year=cal.get(YEAR)
        val month=checkDoubleDigit(cal.get(MONTH))
        val day=checkDoubleDigit(cal.get(DAY_OF_MONTH))

        val hour=checkDoubleDigit(cal.get(HOUR_OF_DAY))
        val min=checkDoubleDigit(cal.get(MINUTE))

        val name=when(type){
            DataBaseType.SQLITE->"SQLite"
            DataBaseType.ROOM->"ROOM"
        }

        return "$year-$month-${day}_${hour}:${min}_${name}_${users.size}.csv"
        //return getFolder()+"/"+"aux.csv"
    }
    private fun checkDoubleDigit(input:Int):String{
        if(input<10)return "0$input"
        return input.toString()
    }


    /*
    FULL RELATED WITH FILES
     */
    fun createBackUp(users:List<User>){
        try {
            val fileOutputStream = context.openFileOutput(getNewFileName(users), Context.MODE_PRIVATE)
            val writer = fileOutputStream.bufferedWriter()
            users.forEach {
                writer.write("${it.id},\"${it.nick}\",\"${it.name}\",\"${it.email}\",\"${it.password}\"")
                writer.newLine()
            }
            writer.flush()
        }
        catch (e: Exception){ e.printStackTrace() }

    }

    fun getBackupList():List<Backup>{
        val res= mutableListOf<Backup>()
        File("").walkBottomUp().forEach { it ->
            checkNameCanBeBackup(it)?.let {
                res.add(it)
            }
        }
        return res
    }


}