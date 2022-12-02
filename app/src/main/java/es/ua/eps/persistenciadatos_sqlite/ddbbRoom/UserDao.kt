package es.ua.eps.persistenciadatos_sqlite.ddbbRoom

import androidx.room.*
import es.ua.eps.persistenciadatos_sqlite.data.User


@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user")
    fun loadAll(): List<User>

    @Query("SELECT * FROM user where nick LIKE :nick AND password LIKE :password LIMIT 1")
    fun loadOneByNickAndPassword(nick: String, password: String): User?

}