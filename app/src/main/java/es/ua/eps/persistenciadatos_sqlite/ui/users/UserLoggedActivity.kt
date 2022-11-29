package es.ua.eps.persistenciadatos_sqlite.ui.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.ua.eps.persistenciadatos_sqlite.data.User
import es.ua.eps.persistenciadatos_sqlite.databinding.ActivityUserLoggedBinding

class UserLoggedActivity : AppCompatActivity() {

    companion object{
        lateinit var selectedUser: User
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bindings = ActivityUserLoggedBinding.inflate(layoutInflater)
        with(bindings){
            setContentView(root)

            tvNick.text = selectedUser.nick
            tvName.text = selectedUser.name

            btnBack.setOnClickListener { finish() }
        }

    }
}