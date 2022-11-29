package es.ua.eps.persistenciadatos_sqlite.dataBase

import es.ua.eps.persistenciadatos_sqlite.data.DataBase
import es.ua.eps.persistenciadatos_sqlite.data.User

class MockValues {
    companion object{

        private val listOfUser = mutableListOf<User>()

        //USER: NEW, EDIT, DELETE
        fun newUser(nick: String, pass: String, name: String,email:String){
            listOfUser.add(User(listOfUser.size+1,nick,pass,name,email))
        }
        fun editUser(id:Int,nick: String, pass: String, name: String){
            for(user in listOfUser){
                if(user.id==id){
                    user.nick=nick
                    user.password=pass
                    user.name=name
                }
            }
        }
        fun deleteUser(user:User){
            listOfUser.remove(user)
        }

        //USER FUNCIONALITY
        fun loginUser(nick: String,pass: String):User?{
            for(user in listOfUser){
                if(user.nick==nick && user.password==pass)
                    return user
            }
            return null
        }

        //RELATED TO LIST
        fun getUserList():List<User>{
            return listOfUser
        }
        fun getUserFromId(id:Int):User?{
            return listOfUser[id]
            //return null
        }
        fun isUserListEmpty():Boolean{
            return listOfUser.isEmpty()
        }

        //MANAGE DDBB
        fun createBackUp(){

        }
        fun restoreBackUp(){

        }

    }
}