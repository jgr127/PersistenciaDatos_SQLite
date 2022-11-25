package es.ua.eps.persistenciadatos_sqlite.ui

import es.ua.eps.persistenciadatos_sqlite.data.DataBase
import es.ua.eps.persistenciadatos_sqlite.data.User

class UpdateUserActivity : GenericUserView(UserViewType.UpdateUser) {

    companion object{
        lateinit var selectedUser:User
    }

    override fun onResume() {
        super.onResume()

        setUserNick(selectedUser.nick)
        setUserPassword(selectedUser.password)
        setUserName(selectedUser.userName)
    }

    override fun onButtonAction(nick: String, pass: String, name: String) {
        DataBase.editUser(selectedUser.id,nick, pass, name)
        finish()
    }


}