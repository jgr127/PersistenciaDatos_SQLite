package es.ua.eps.persistenciadatos_sqlite.data

import android.content.Context
import es.ua.eps.persistenciadatos_sqlite.dataBase.UserSQLiteDatabase

class DataBase() {
    companion object{
        //CONTROL
        var currentType:DataBaseType=DataBaseType.SQLITE
        private fun getCurrentControler(context: Context):DataBaseInterface{
            return when(currentType){
                DataBaseType.SQLITE-> UserSQLiteDatabase.getInstance(context)
                //DataBaseType.ROOM-> TODO
                //DataBaseType.MOCK_VALUES-> TODO
            }
        }

        //USER: NEW, EDIT, DELETE
        fun newUser(context: Context,nick: String, pass: String, name: String, email: String){
            getCurrentControler(context).newUser(nick, pass, name,email)
        }
        fun editUser(context: Context,id:Int,nick: String, pass: String, name: String, email: String){
            getCurrentControler(context).editUser(id,nick,pass,name,email)
        }
        fun deleteUser(context: Context,user:User){
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

        //MANAGE DDBB
        fun createBackUp(context: Context){
            getCurrentControler(context).restoreBackUp()
        }
        fun restoreBackUp(context: Context){
            getCurrentControler(context).restoreBackUp()
        }
    }
}
enum class DataBaseType{
    SQLITE//,ROOM,MOCK_VALUES
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

    //MANAGE DDBB
    fun createBackUp()
    fun restoreBackUp()
}