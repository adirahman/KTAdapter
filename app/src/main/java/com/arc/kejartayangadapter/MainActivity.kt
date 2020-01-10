package com.arc.kejartayangadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import com.arc.ktadapter.GenericAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val genericAdapter = GenericAdapter<ContactModel>(R.layout.item_list_contact,BR.listItemViewModel)

        genericAdapter.setOnListItemViewClickListener(object : GenericAdapter.OnListItemViewClickListener{
            override fun onClick(view: View, position: Int) {
                Toast.makeText(view.context,"Click ar row $position", Toast.LENGTH_SHORT).show()
            }
        })

        val listContact = ArrayList<ContactModel>()
        listContact.add(ContactModel("ucok","1234"))
        listContact.add(ContactModel("ucok","1234"))
        listContact.add(ContactModel("ucok","1234"))

        rv.adapter = genericAdapter
        genericAdapter.addItems(listContact)
    }
}
