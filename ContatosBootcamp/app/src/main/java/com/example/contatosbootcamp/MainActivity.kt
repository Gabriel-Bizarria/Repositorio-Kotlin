package com.example.contatosbootcamp

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    //Defining standart values to checking
    val REQUEST_CONTACT = 1
    val LINEAR_LAYOUT_VERTICAL = 1

    //Create the activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Checking if is permitted by the user to get his contacts
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.READ_CONTACTS),
            REQUEST_CONTACT)
        } else {
            setContacts()
        }
    }

    //Check what is the result of the status of permission, and if its Ok, its set the contacts
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_CONTACT) setContacts()
    }

    //Function that sets the contacts based on contact list of telephone
    private fun setContacts() {
        val contactList: ArrayList<Contact> = ArrayList()

        val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, null, null, null)

        if(cursor != null){
            while(cursor.moveToNext()){
                contactList.add(Contact(
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                ))
            }
            cursor.close()
        }

        //Instances of adapter and recycler view on the Main Activity
        val adapter = ContactsAdapter(contactList)
        val contactRecyclerView = findViewById<RecyclerView>(R.id.contacts_recycler_view)

        contactRecyclerView.layoutManager = LinearLayoutManager(this,
            LINEAR_LAYOUT_VERTICAL, false
        )

        contactRecyclerView.adapter = adapter
    }
}