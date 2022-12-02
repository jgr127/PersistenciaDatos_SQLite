package es.ua.eps.persistenciadatos_sqlite.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Switch
import android.widget.Toast
import es.ua.eps.persistenciadatos_sqlite.R
import es.ua.eps.persistenciadatos_sqlite.data.DataBase
import es.ua.eps.persistenciadatos_sqlite.data.DataBaseType
import es.ua.eps.persistenciadatos_sqlite.databinding.ActivityLoginBinding
import es.ua.eps.persistenciadatos_sqlite.ui.users.UserLoggedActivity
import es.ua.eps.persistenciadatos_sqlite.ui.users.UserManageActivity

class LoginActivity : AppCompatActivity() {

    lateinit var activity:Activity
    lateinit var switch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity=this

        val bindings = ActivityLoginBinding.inflate(layoutInflater)
        with(bindings){
            setContentView(root)

            btnLogin.setOnClickListener {
                val user=DataBase.loginUser(activity,inputUserNick.text.toString(),inputUserPassword.text.toString())
                if(user==null){
                    Toast.makeText(baseContext,"Usuario y/o contraseña incorrecta!",Toast.LENGTH_SHORT).show()
                }
                else{
                    inputUserNick.text?.clear()
                    inputUserPassword.text?.clear()

                    UserLoggedActivity.selectedUser=user
                    startActivity(Intent(activity,UserLoggedActivity::class.java))
                }
            }

            switch=cambiarModo
            updateSwitchUI()

            btnClose.setOnClickListener {
                finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_login, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.createBackUp -> {
                DataBase.createBackUp(activity)
                true
            }
            R.id.restoreBackUp -> {
                DataBase.restoreBackUp(activity)
                true
            }
            R.id.manageUsers -> {
                startActivity(Intent(activity,UserManageActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateSwitchUI(){
        switch.text = when(DataBase.currentType){
            DataBaseType.SQLITE->"DDBB: SQLite"
            DataBaseType.ROOM->"DDBB: ROOM"
        }
        switch.isSelected = when(DataBase.currentType){
            DataBaseType.SQLITE-> false
            DataBaseType.ROOM-> true
        }

        switch.setOnClickListener {
            DataBase.changeTypeDDBB()
            updateSwitchUI()
        }
    }

}