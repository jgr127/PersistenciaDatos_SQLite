package es.ua.eps.persistenciadatos_sqlite.data

import java.lang.Exception

data class User(
    var id:Int,
    var nick:String,
    var password:String,
    var name:String,
    var email:String
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