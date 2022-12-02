package es.ua.eps.persistenciadatos_sqlite.ddbbRoom

import android.content.Context
import androidx.room.Room
import es.ua.eps.persistenciadatos_sqlite.ddbbGeneral.DataBaseInterface
import es.ua.eps.persistenciadatos_sqlite.data.User
import es.ua.eps.persistenciadatos_sqlite.ddbbSQLite.SQLiteDatabaseName

const val RoomDatabaseName:String="usersRoom.db"

class UserRoomDatabase(val context: Context) : DataBaseInterface {

    //GENERAL
    private fun getAppDatabase():RoomAppDatabase{
        val db: RoomAppDatabase = Room.databaseBuilder(context,RoomAppDatabase::class.java, RoomDatabaseName).allowMainThreadQueries().build()
        return db
    }

    //USER: NEW, EDIT, DELETE
    override fun newUser(nick:String,pass:String,name:String,email:String){
        val db = getAppDatabase()
        db.userDao().insert(User(id=0,nick=nick,password=pass,name=name,email=email))
    }
    override fun editUser(id:Int,nick:String,pass:String,name:String,email:String){
        val db = getAppDatabase()
        db.userDao().update(User(id=id,nick=nick,password=pass,name=name,email=email))
    }
    override fun deleteUser(id:Int){
        val db = getAppDatabase()
        db.userDao().delete(User(id=id,nick="",password="",name="",email=""))
    }

    //USER FUNCIONALITY
    override fun loginUser(nick: String, pass: String): User? {
        val db = getAppDatabase()
        return db.userDao().loadOneByNickAndPassword(nick,pass)
    }

    //RELATED TO LIST
    override fun getUserList(): List<User> {
        val db = getAppDatabase()
        return db.userDao().loadAll()
    }

    //RELATED TO LIST
    override fun reloadFullDataBase(newUsers: List<User>) {
        val db = getAppDatabase()
        db.userDao().deleteFullTable()
        for(user in newUsers)
            db.userDao().insert(user)
    }


    //PATRON SINGLETON (para poder usarlo de forma "estatica" donde quiera)
    companion object{
        private var mDatabase:UserRoomDatabase?=null

        fun getInstance(context: Context):UserRoomDatabase{
            if(mDatabase==null){
                mDatabase=UserRoomDatabase(context)
            }
            return mDatabase!!
        }
    }

}