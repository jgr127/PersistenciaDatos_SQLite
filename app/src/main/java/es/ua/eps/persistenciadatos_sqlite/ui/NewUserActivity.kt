package es.ua.eps.persistenciadatos_sqlite.ui

import es.ua.eps.persistenciadatos_sqlite.data.DataBase

class NewUserActivity : GenericUserView(UserViewType.NewUser) {

    override fun onButtonAction(nick: String, pass: String, name: String) {
        DataBase.newUser(nick, pass, name)
        finish()
    }

}