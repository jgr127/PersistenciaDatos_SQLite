package es.ua.eps.persistenciadatos_sqlite.ui.users.userList

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import es.ua.eps.persistenciadatos_sqlite.R
import es.ua.eps.persistenciadatos_sqlite.data.User


class UserAdapter(activity:Activity, val users:List<User>) : ArrayAdapter<User?>(activity,0) {

   override fun getCount(): Int {
      return users.size+1
   }

   override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
      if(position==0){
         return LayoutInflater.from(context).inflate(R.layout.row_user_list_header, parent, false)
      }
      val view = LayoutInflater.from(context).inflate(R.layout.row_user_list, parent, false)
      val tvNick = view.findViewById<TextView>(R.id.tvNick)
      val tvName = view.findViewById<TextView>(R.id.tvName)

      val user = users[position-1]
      tvNick.text=user.nick
      tvName.text=user.name

      return view
   }

}