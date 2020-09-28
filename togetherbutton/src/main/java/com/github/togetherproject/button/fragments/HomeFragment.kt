package com.github.togetherproject.button.fragments

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.github.togetherproject.button.R
import com.github.togetherproject.button.databinding.FragmentHomeBinding
import com.github.togetherproject.button.utils.PermissionUltis.hasCallPermissions
import com.github.togetherproject.button.utils.PermissionUltis.hasReadPermissions
import com.github.togetherproject.button.utils.call

class HomeFragment(val manager: FragmentManager) : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentHomeBinding
    private var phoneNo: String = ""

    companion object {
        const val REQUEST_CALL: Int = 101
        const val REQUEST_READ: Int = 201
        const val GET_CONTACT: Int = 301
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.btnCallForHelp.setOnClickListener(this)
        binding.btnSafeContact.setOnClickListener(this)

        return binding.root
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnCallForHelp -> {
                manager.beginTransaction().replace(R.id.fragmentContainer, AskForHelpFragment(manager)).commit()
            }

            R.id.btnSafeContact -> {
                if (hasReadPermissions(context!!)) fetchPhoneNo()
                else {
                    requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_READ)
                }
            }
        }
    }

    private fun fetchPhoneNo() {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        startActivityForResult(intent, GET_CONTACT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GET_CONTACT) {
            if (data?.data != null) {
                val contactUri = data.data;
                val crContacts = context!!.contentResolver.query(contactUri!!, null, null,
                    null, null);

                crContacts!!.moveToFirst()
                val id = crContacts.getString(crContacts.getColumnIndex(ContactsContract.Contacts._ID));

                if (Integer.parseInt(crContacts.getString(crContacts.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    val crPhones = context!!.contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                + " = ?", arrayOf(id), null)

                    crPhones!!.moveToFirst()
                    phoneNo = crPhones.getString(crPhones.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER))

                    if (hasCallPermissions(context!!)) this.call(phoneNo)
                    else {
                        requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CALL)
                    }

                    crPhones.close()
                }
                crContacts.close()
            }
        } else this.call(phoneNo)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CALL) this.call(phoneNo)
        if (requestCode == REQUEST_READ) fetchPhoneNo()
    }

}