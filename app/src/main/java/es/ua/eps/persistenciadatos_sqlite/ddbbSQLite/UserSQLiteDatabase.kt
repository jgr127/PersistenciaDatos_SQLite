package es.ua.eps.persistenciadatos_sqlite.ddbbSQLite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Environment
import android.provider.BaseColumns
import es.ua.eps.persistenciadatos_sqlite.ddbbGeneral.DataBaseInterface
import es.ua.eps.persistenciadatos_sqlite.data.User
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.channels.FileChannel


const val SQLiteDatabaseName:String="usersSQLite.db"

class UserSQLiteDatabase(context: Context) : SQLiteOpenHelper(context,SQLiteDatabaseName,null,1), DataBaseInterface {

    //USER TABLE
    object UserEntry : BaseColumns {
        const val TABLE_NAME = "user"
        const val ID = "id"
        const val NICK = "nick"
        const val NAME = "name"
        const val EMAIL = "email"
        const val PASS = "password"
    }

    //SQLiteOpenHelper
    override fun onCreate(sqLiteDatabase: SQLiteDatabase?) {
        val createUsersSQL="CREATE TABLE  ${UserEntry.TABLE_NAME} ("+
                "${UserEntry.ID} INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "${UserEntry.NICK} TEXT NOT NULL,"+
                "${UserEntry.NAME} TEXT NOT NULL,"+
                "${UserEntry.EMAIL} TEXT NOT NULL,"+
                "${UserEntry.PASS} TEXT NOT NULL"+
                //",UNIQUE (${UserEntry.NICK})" +
                ")"
        sqLiteDatabase?.execSQL(createUsersSQL)
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //DE MOMENTO NO ME INTERESA
    }

    //USER: NEW, EDIT, DELETE
    override fun newUser(nick:String,pass:String,name:String,email:String){
        val db = writableDatabase
        db.execSQL("INSERT INTO ${UserEntry.TABLE_NAME}(${UserEntry.NICK},${UserEntry.NAME},${UserEntry.EMAIL},${UserEntry.PASS})" +
                "VALUES('$nick','$name','$email','$pass')")
    }
    override fun editUser(id:Int,nick:String,pass:String,name:String,email:String){
        val values=ContentValues()
        values.put(UserEntry.NICK,nick)
        values.put(UserEntry.NAME,name)
        values.put(UserEntry.PASS,pass)
        values.put(UserEntry.EMAIL,email)

        val db = writableDatabase
        db.update(UserEntry.TABLE_NAME,values,"${UserEntry.ID}==$id",null)
    }
    override fun deleteUser(id:Int){
        val db = writableDatabase
        db.delete(UserEntry.TABLE_NAME,"${UserEntry.ID}==$id",null)
    }

    //USER FUNCIONALITY
    override fun loginUser(nick: String, pass: String): User? {

        val db = writableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${UserEntry.TABLE_NAME} WHERE ${UserEntry.NICK}='$nick' AND ${UserEntry.PASS}='$pass'",null)
        if(cursor.moveToFirst()) {
            return User(
                id = cursor.getInt(0),
                nick = cursor.getString(1),
                name = cursor.getString(2),
                email = cursor.getString(3),
                password = cursor.getString(4)
            )
        }

        return null
    }

    //RELATED TO LIST
    override fun getUserList(): List<User> {
        val res=mutableListOf<User>()

        val db = writableDatabase
        val columns=arrayOf(UserEntry.ID,UserEntry.NICK,UserEntry.NAME,UserEntry.EMAIL,UserEntry.PASS)
        val cursor = db.query(UserEntry.TABLE_NAME,columns,null,null,null,null,null)
        if(cursor.moveToFirst()) {
            do {
                res.add(User(
                    id = cursor.getInt(0),
                    nick = cursor.getString(1),
                    name = cursor.getString(2),
                    email = cursor.getString(3),
                    password = cursor.getString(4)
                ))
            }while (cursor.moveToNext())
        }

        return res
    }


    //PATRON SINGLETON (para poder usarlo de forma "estatica" donde quiera)
    companion object{
        private var mDatabase:UserSQLiteDatabase?=null

        fun getInstance(context: Context):UserSQLiteDatabase{
            if(mDatabase==null){
                mDatabase=UserSQLiteDatabase(context)
            }
            return mDatabase!!
        }
    }

}