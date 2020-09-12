package com.github.togetherproject.button.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.togetherproject.button.Utils.PermissionUltis.hasPermissions
import com.github.togetherproject.button.R
import com.github.togetherproject.button.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        setUpClickListeners()

        return binding.root
    }

    private fun handleDidntGrantPermissions() {
        Toast.makeText(
            context,
            context?.getString(R.string.permissions_denied_text),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setUpClickListeners() {
        setUpCallClickListener()
        setUpContactClickListener()
    }

    private fun setUpCallClickListener() {
        binding.btnCallForHelp.setOnClickListener {
            if (!hasPermissions(context!!))
                handleDidntGrantPermissions()
            else {
                val intent = Intent(Intent.ACTION_CALL)

                intent.data = Uri.parse("tel:${context!!.getString(R.string.emergency_number)}")
                context?.startActivity(intent)
            }
        }
    }

    private fun setUpContactClickListener() {
        binding.btnSafeContact.setOnClickListener {
            if (!hasPermissions(context!!))
                handleDidntGrantPermissions()
            else {
                val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
                context?.startActivity(intent)
            }
        }
    }
}