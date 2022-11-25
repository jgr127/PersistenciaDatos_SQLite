package es.ua.eps.persistenciadatos_sqlite

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import es.ua.eps.persistenciadatos_sqlite.data.DataBase
import es.ua.eps.persistenciadatos_sqlite.data.User
import es.ua.eps.persistenciadatos_sqlite.databinding.ActivityMainBinding
import es.ua.eps.persistenciadatos_sqlite.ui.NewUserActivity
import es.ua.eps.persistenciadatos_sqlite.ui.UpdateUserActivity
import es.ua.eps.persistenciadatos_sqlite.ui.UserListActivity

class MainActivity : AppCompatActivity() {

    //Basic control
    lateinit var binding: ActivityMainBinding
    lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity=this

        binding=ActivityMainBinding.inflate(layoutInflater)
        with(binding){
            setContentView(root)

            btnNewUser.setOnClickListener {
                startActivity(Intent(activity,NewUserActivity::class.java))
            }

            btnListUser.setOnClickListener {
                startActivity(Intent(activity,UserListActivity::class.java))
            }

            btnBack.setOnClickListener { finish() }
        }
    }
    override fun onResume() {
        super.onResume()
        updateUserListUI()
    }


    //USER LIST UI
    fun updateUserListUI(){
        with(binding){
            if(DataBase.isUserListEmpty()){
                spinnerUsers.adapter=ArrayAdapter(activity,android.R.layout.simple_dropdown_item_1line,mutableListOf<String>())
                spinnerUsers.isEnabled=false
                btnUpdateUser.isEnabled=false
                btnDeleteUser.isEnabled=false
            }
            else{
                spinnerUsers.isEnabled=true
                btnUpdateUser.isEnabled=true
                btnDeleteUser.isEnabled=true

                spinnerUsers.adapter=ArrayAdapter(activity,android.R.layout.simple_dropdown_item_1line,DataBase.getUserList())
                var currentSelectedUser:User?=null
                spinnerUsers.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        currentSelectedUser= parent?.selectedItem as User
                    }
                    override fun onNothingSelected(p0: AdapterView<*>?) {}
                }

                btnUpdateUser.setOnClickListener {
                    currentSelectedUser?.let {
                        UpdateUserActivity.selectedUser=it
                        startActivity(Intent(activity,UpdateUserActivity::class.java))
                    }
                }
                btnDeleteUser.setOnClickListener {
                    currentSelectedUser?.let {
                        val builder = AlertDialog.Builder(activity)
                        builder.setTitle("Delete User")
                        builder.setMessage("¿Are you sure you want to delete: ${it.nick} (${it.userName})?")
                            .setPositiveButton("YES") { dialog, id ->
                                DataBase.deleteUser(it)
                                updateUserListUI()
                            }
                            .setNegativeButton("CANCEL") { dialog, id ->
                                // User cancelled the dialog
                            }
                        builder.create()
                        builder.show()
                    }
                }

            }
        }
    }


}