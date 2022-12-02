package es.ua.eps.persistenciadatos_sqlite.ddbbRoom

import androidx.room.Database
import androidx.room.RoomDatabase
import es.ua.eps.persistenciadatos_sqlite.data.User


@Database(entities = [User::class], version = 1)
abstract class RoomAppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}