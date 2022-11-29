package es.ua.eps.persistenciadatos_sqlite.ui.users.userList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.ua.eps.persistenciadatos_sqlite.data.DataBase
import es.ua.eps.persistenciadatos_sqlite.databinding.ActivityUserListBinding

class UserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activity=this
        val bindings = ActivityUserListBinding.inflate(layoutInflater)
        with(bindings){
            setContentView(root)

            listview.adapter= UserAdapter(activity,DataBase.getUserList(activity))

            btnBack.setOnClickListener { finish() }

        }
    }
}