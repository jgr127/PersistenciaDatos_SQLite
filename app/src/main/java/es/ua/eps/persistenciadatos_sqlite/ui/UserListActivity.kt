package es.ua.eps.persistenciadatos_sqlite.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.ua.eps.persistenciadatos_sqlite.data.DataBase
import es.ua.eps.persistenciadatos_sqlite.databinding.UserListBinding
import es.ua.eps.persistenciadatos_sqlite.ui.adapter.UserRecyclerViewAdapter

class UserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bindings = UserListBinding.inflate(layoutInflater)
        with(bindings){
            setContentView(root)

            recyclerView.adapter=UserRecyclerViewAdapter(DataBase.getUserList())

            btnBack.setOnClickListener { finish() }

        }
    }
}