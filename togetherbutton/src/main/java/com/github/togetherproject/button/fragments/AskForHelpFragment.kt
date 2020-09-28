package com.github.togetherproject.button.fragments

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.github.togetherproject.button.R
import com.github.togetherproject.button.databinding.FragmentAskForHelpBinding
import com.github.togetherproject.button.utils.PermissionUltis
import com.github.togetherproject.button.utils.call
import com.github.togetherproject.button.utils.PermissionUltis.hasCallPermissions

class AskForHelpFragment(val manager: FragmentManager) : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentAskForHelpBinding
    private var num: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAskForHelpBinding.inflate(inflater)
        binding.btn153.setOnClickListener(this)
        binding.btn180.setOnClickListener(this)
        binding.btnServices.setOnClickListener(this)

        return binding.root
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_153 -> {
                num = "153"
                if (hasCallPermissions(context!!)) this.call(num)
                else {
                    requestPermissions(arrayOf(Manifest.permission.CALL_PHONE),
                        HomeFragment.REQUEST_CALL
                    )
                }
            }

            R.id.btn_180 -> {
                num = "180"
                if (hasCallPermissions(context!!)) this.call(num)
                else {
                    requestPermissions(arrayOf(Manifest.permission.CALL_PHONE),
                        HomeFragment.REQUEST_CALL
                    )
                }
            }

            R.id.btnServices -> manager.beginTransaction()
                .replace(R.id.fragmentContainer, HelpServicesFragment(manager)).commit()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == HomeFragment.REQUEST_CALL) {
            this.call(num)
        }
    }

}