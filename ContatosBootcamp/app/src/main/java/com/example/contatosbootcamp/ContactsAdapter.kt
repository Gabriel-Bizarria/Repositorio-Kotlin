package com.example.contatosbootcamp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//Creation of adapter that will deal with every item of the list to be shown on layout by recycler view.

class ContactsAdapter(val contactsList: List<Contact>):
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>(){

    //Function that inflates de view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_view, parent, false)

        return ViewHolder(view)
    }

    //Function that identify the item on the logic list, and hold it to throw on the layout.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(contactsList[position])
    }

    //Simple function that return the size of the list
    override fun getItemCount(): Int {
        return contactsList.size
    }

    //Creates the view holder
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        //Function that binds the layout with the contact list provided by the code
        fun bindItem(contact: Contact){
            val textName = itemView.findViewById<TextView>(R.id.contact_name)
            val textPhone = itemView.findViewById<TextView>(R.id.contact_phone)

            textName.text = contact.name
            textPhone.text = contact.phoneNumber

        }
    }
}