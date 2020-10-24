package com.github.togetherproject.button.module.help_screens.view

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.togetherproject.button.databinding.FragmentHomeBinding
import com.github.togetherproject.button.module.help_screens.viewmodel.HomeViewModel
import com.github.togetherproject.button.utils.PermissionUltis.hasCallPermissions
import com.github.togetherproject.button.utils.PermissionUltis.hasReadPermissions

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var phoneNo: String

    companion object {
        const val REQUEST_CALL: Int = 101
        const val REQUEST_READ: Int = 201
        const val GET_CONTACT: Int = 301
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setUpClickListeners()
    }

    private fun FragmentHomeBinding.setUpClickListeners() {
        btnCallForHelp.setOnClickListener{
            findNavController()
                .navigate(
                    HomeFragmentDirections
                        .actionHomeFragmentToAskForHelpFragment()
                )
        }

        btnSafeContact.setOnClickListener{
            if (hasReadPermissions(requireContext())) fetchPhoneNo()
            else requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                REQUEST_READ
            )
        }
    }

    private fun fetchPhoneNo() {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        startActivityForResult(intent, GET_CONTACT)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GET_CONTACT) {
            if (data?.data != null) {
                val contactUri = data.data;
                val crContacts = requireContext()
                    .contentResolver
                    .query(contactUri!!, null, null, null, null);

                crContacts!!.moveToFirst()
                val id = crContacts.getString(crContacts.getColumnIndex(ContactsContract.Contacts._ID));

                if (Integer.parseInt(
                        crContacts.getString(
                            crContacts.getColumnIndex(
                                ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0
                ) {
                    val crPhones = requireContext()
                        .contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            arrayOf(id),
                            null
                        )

                    crPhones!!.moveToFirst()
                    phoneNo = crPhones.getString(
                        crPhones.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                    )

                    if (hasCallPermissions(requireContext())) viewModel.call(phoneNo, requireContext())
                    else requestPermissions(
                        arrayOf(Manifest.permission.CALL_PHONE),
                        REQUEST_CALL
                    )

                    crPhones.close()
                }
                crContacts.close()
            }
        } else viewModel.call(phoneNo, requireContext())
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CALL) viewModel.call(phoneNo, requireContext())
        if (requestCode == REQUEST_READ) fetchPhoneNo()
    }
}