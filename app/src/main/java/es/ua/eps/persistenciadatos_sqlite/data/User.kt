package es.ua.eps.persistenciadatos_sqlite.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.Exception

@Entity
data class User(
    @PrimaryKey var id:Int,
    @ColumnInfo(name = "nick") var nick:String,
    @ColumnInfo(name = "password") var password:String,
    @ColumnInfo(name = "name") var name:String,
    @ColumnInfo(name = "email") var email:String
) {

    override fun toString(): String {
        return nick
    }

    override fun equals(other: Any?): Boolean {
        try{
            (other as User).let {
                return it.id==this.id
            }
        }catch (e:Exception){e.printStackTrace()}
        return false
    }

}