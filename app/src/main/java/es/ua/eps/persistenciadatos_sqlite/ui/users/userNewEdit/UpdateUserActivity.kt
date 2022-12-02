package es.ua.eps.persistenciadatos_sqlite.ui.users.userNewEdit

import es.ua.eps.persistenciadatos_sqlite.data.User
import es.ua.eps.persistenciadatos_sqlite.ddbbGeneral.DataBase

class UpdateUserActivity : GenericUserView(UserViewType.UpdateUser) {

    companion object{
        lateinit var selectedUser:User
    }

    override fun onResume() {
        super.onResume()

        setUserNick(selectedUser.nick)
        setUserPassword(selectedUser.password)
        setUserName(selectedUser.name)
    }

    override fun onButtonAction(nick: String, pass: String, name: String,email:String) {
        DataBase.editUser(activity,selectedUser.id,nick, pass, name,email)
        finish()
    }


}