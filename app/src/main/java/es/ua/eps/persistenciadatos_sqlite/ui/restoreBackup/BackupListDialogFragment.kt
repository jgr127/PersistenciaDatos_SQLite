package es.ua.eps.persistenciadatos_sqlite.ui.restoreBackup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.DialogFragment
import es.ua.eps.persistenciadatos_sqlite.R
import es.ua.eps.persistenciadatos_sqlite.data.Backup
import es.ua.eps.persistenciadatos_sqlite.ddbbGeneral.DataBase
import es.ua.eps.persistenciadatos_sqlite.ui.users.userList.UserAdapter

class BackupListDialogFragment(val backups:List<Backup>): DialogFragment() {

    //UI
    lateinit var listview:ListView
    lateinit var btnBack:Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_user_list, container, false);
        listview=view.findViewById(R.id.listview)
        btnBack=view.findViewById(R.id.btnBack)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listview.adapter= BackupAdapter(requireActivity(),backups)
        btnBack.setOnClickListener {
            dismiss()
        }
    }

    override fun onResume() {
        super.onResume()

        requireDialog().window?.attributes?.let {
            it.width = ViewGroup.LayoutParams.MATCH_PARENT;
            it.height = ViewGroup.LayoutParams.MATCH_PARENT;
            requireDialog().window?.setAttributes(it)
        }
    }

}