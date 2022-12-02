package es.ua.eps.persistenciadatos_sqlite.ui.users.userNewEdit

import es.ua.eps.persistenciadatos_sqlite.ddbbGeneral.DataBase


class NewUserActivity : GenericUserView(UserViewType.NewUser) {

    override fun onButtonAction(nick: String, pass: String, name: String,email:String) {
        DataBase.newUser(activity,nick, pass, name,email)
        finish()
    }

}