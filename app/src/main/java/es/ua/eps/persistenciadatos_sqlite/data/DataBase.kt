package es.ua.eps.persistenciadatos_sqlite.data

class DataBase {
    companion object{

        private val listOfUser = mutableListOf<User>()

        //CONTRL
        fun newUser(nick: String, pass: String, name: String){
            listOfUser.add(User(id = listOfUser.size+1,nick,pass,name))
        }
        fun editUser(id:Int,nick: String, pass: String, name: String){
            for(user in listOfUser){
                if(user.id==id){
                    user.nick=nick
                    user.password=pass
                    user.userName=name
                }
            }
        }
        fun deleteUser(user:User){
            listOfUser.remove(user)
        }

        //RELATED TO LIST
        fun getUserList():List<User>{
            return listOfUser
        }
        fun getUserFromId(id:Int):User?{
            return listOfUser[id]
            //return null
        }

        //AUXILIAR
        fun isUserListEmpty():Boolean{
            return listOfUser.isEmpty()
        }

    }
}