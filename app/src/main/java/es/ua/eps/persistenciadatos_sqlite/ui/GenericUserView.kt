package es.ua.eps.persistenciadatos_sqlite.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.ua.eps.persistenciadatos_sqlite.data.User
import es.ua.eps.persistenciadatos_sqlite.databinding.UserViewBinding

abstract class GenericUserView(val type:UserViewType) : AppCompatActivity() {

    //UI
    lateinit var binding: UserViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = UserViewBinding.inflate(layoutInflater)
        with(binding){
            setContentView(binding.root)


            btnNewUser.text = when(type){
                UserViewType.NewUser->"New user"
                UserViewType.UpdateUser->"Update user"
            }
            btnNewUser.setOnClickListener {
                val nick=inputUserNick.text.toString()
                val pass=inputUserPassword.text.toString()
                val name=inputUserName.text.toString()

                inputUserNick.error=null
                inputUserPassword.error=null
                inputUserName.error=null
                if(nick.length<3){
                    inputUserNick.error="Too short!"
                }
                else if(pass.length<3){
                    inputUserPassword.error="Too short!"
                }
                else if(name.length<3){
                    inputUserName.error="Too short!"
                }
                else{
                    onButtonAction(nick, pass, name)
                }
            }

            btnBack.setOnClickListener {
                finish()
            }

        }

    }

    //Important functions
    abstract fun onButtonAction(nick:String,pass:String,name:String)

    /*
    OPTIONAL FUNCTIONS
     */
    fun setUserNick(input:String){
        binding.inputUserNick.setText(input)
    }
    fun setUserName(input:String){
        binding.inputUserName.setText(input)
    }
    fun setUserPassword(input:String){
        binding.inputUserPassword.setText(input)
    }


}