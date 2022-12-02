package es.ua.eps.persistenciadatos_sqlite.ddbbGeneral

import android.app.Activity
import android.content.Context
import es.ua.eps.persistenciadatos_sqlite.data.Backup
import es.ua.eps.persistenciadatos_sqlite.data.User
import es.ua.eps.persistenciadatos_sqlite.ddbbRoom.RoomAppDatabase
import es.ua.eps.persistenciadatos_sqlite.ddbbRoom.UserRoomDatabase
import es.ua.eps.persistenciadatos_sqlite.ddbbSQLite.UserSQLiteDatabase
import es.ua.eps.persistenciadatos_sqlite.ui.restoreBackup.BackupListDialogFragment

class DataBase {
    companion object{
        //CONTROL
        var currentType:DataBaseType=DataBaseType.SQLITE
        private fun getCurrentControler(context: Context):DataBaseInterface{
            return when(currentType){
                DataBaseType.SQLITE-> UserSQLiteDatabase.getInstance(context)
                DataBaseType.ROOM-> UserRoomDatabase.getInstance(context)
            }
        }
        fun changeTypeDDBB(){
            when(currentType){
                DataBaseType.SQLITE-> this.currentType=DataBaseType.ROOM
                DataBaseType.ROOM-> this.currentType=DataBaseType.SQLITE
            }
        }

        //USER: NEW, EDIT, DELETE
        fun newUser(context: Context,nick: String, pass: String, name: String, email: String){
            getCurrentControler(context).newUser(nick, pass, name,email)
        }
        fun editUser(context: Context,id:Int,nick: String, pass: String, name: String, email: String){
            getCurrentControler(context).editUser(id,nick,pass,name,email)
        }
        fun deleteUser(context: Context,user: User){
            getCurrentControler(context).deleteUser(user.id)
        }

        //USER FUNCIONALITY
        fun loginUser(context: Context,nick: String,pass: String):User?{
            return getCurrentControler(context).loginUser(nick, pass)
        }

        //RELATED TO LIST
        fun getUserList(context: Context):List<User>{
            return getCurrentControler(context).getUserList()
        }

        //RELATED TO LIST
        fun reloadFullDataBase(context: Context, users:List<User>){
            return getCurrentControler(context).reloadFullDataBase(users)
        }

        //MANAGE DDBB
        fun createBackUp(context: Context){
            DataBaseManage(context).createBackUp(getUserList(context))
        }
        fun restoreBackUp(context: Context): List<Backup>{
            return DataBaseManage(context).getBackupList()
        }
    }
}
enum class DataBaseType{
    SQLITE,ROOM
}
interface DataBaseInterface{
    //USER: NEW, EDIT, DELETE
    fun newUser(nick: String, pass: String, name: String,email:String)
    fun editUser(id:Int,nick: String, pass: String, name: String,email: String)
    fun deleteUser(id:Int)

    //USER FUNCIONALITY
    fun loginUser(nick: String,pass: String):User?

    //RELATED TO LIST
    fun getUserList():List<User>

    //RELOAD DDBB
    fun reloadFullDataBase(newUsers:List<User>)

    //MANAGE DDBB
    //fun createBackUp()
    //fun restoreBackUp()
}