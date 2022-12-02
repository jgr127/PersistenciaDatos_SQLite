package es.ua.eps.persistenciadatos_sqlite.ui.restoreBackup

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import es.ua.eps.persistenciadatos_sqlite.R
import es.ua.eps.persistenciadatos_sqlite.data.Backup
import es.ua.eps.persistenciadatos_sqlite.ddbbGeneral.DataBase
import es.ua.eps.persistenciadatos_sqlite.ddbbGeneral.DataBaseManage


class BackupAdapter(val activity:Activity, val backups:List<Backup>) : ArrayAdapter<Backup?>(activity,0) {

   override fun getCount(): Int {
      return backups.size+1
   }

   override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
      if(position==0){
         return LayoutInflater.from(context).inflate(R.layout.row_backup_list_header, parent, false)
      }
      val view = LayoutInflater.from(context).inflate(R.layout.row_backup_list, parent, false)

      val tvFecha = view.findViewById<TextView>(R.id.tvFecha)
      val tvHora = view.findViewById<TextView>(R.id.tvHora)
      val tvTipo = view.findViewById<TextView>(R.id.tvTipo)
      val tvUsuarios = view.findViewById<TextView>(R.id.tvUsuarios)
      val btnRecuperar = view.findViewById<Button>(R.id.btnRecuperar)

      val backup = backups[position-1]
      tvFecha.text=backup.fecha
      tvHora.text=backup.hora
      tvTipo.text=backup.tipo
      tvUsuarios.text=backup.numeroUsuarios

      btnRecuperar.setOnClickListener {
         val users = DataBaseManage(activity).getBackupUsers(backup.fileName)
         DataBase.reloadFullDataBase(activity,users)
         Toast.makeText(activity,"DDBB HAS BEEN RELOAD!",Toast.LENGTH_SHORT).show()
      }
      
      return view
   }

}