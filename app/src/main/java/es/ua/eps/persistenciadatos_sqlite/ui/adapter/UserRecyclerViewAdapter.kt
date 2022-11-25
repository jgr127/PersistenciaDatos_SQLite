package es.ua.eps.persistenciadatos_sqlite.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.ua.eps.persistenciadatos_sqlite.R
import es.ua.eps.persistenciadatos_sqlite.data.User

class UserRecyclerViewAdapter(val users:List<User>): RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewController>() {

    private val holders= mutableListOf<UserViewController>()

    override fun getItemViewType(position: Int): Int { return position }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewController {
        if(viewType==0){
            return UserViewController(header=true,view=LayoutInflater.from(parent.context).inflate(R.layout.row_user_list_header, parent, false))
        }
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_user_list, parent, false)
        val holder = UserViewController(view)
        holders.add(holder)
        return holder
    }
    override fun getItemCount(): Int {
        return users.size+1
    }
    override fun onBindViewHolder(holder: UserViewController, position: Int) {
        holder.bind(users[position])
    }



    class UserViewController(val view: View,val header:Boolean=false): RecyclerView.ViewHolder(view) {

        //control variables
        private lateinit var tvNick: TextView
        private lateinit var tvName: TextView

        init {
            if (!header){
                tvNick=view.findViewById(R.id.tvNick)
                tvName=view.findViewById(R.id.tvName)
            }
        }

        fun bind(user:User){
            tvNick.text=user.nick
            tvName.text=user.userName
        }

    }

}