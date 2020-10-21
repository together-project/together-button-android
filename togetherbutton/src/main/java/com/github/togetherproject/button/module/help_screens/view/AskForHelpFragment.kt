package com.github.togetherproject.button.module.help_screens.view

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.togetherproject.button.R
import com.github.togetherproject.button.databinding.FragmentAskForHelpBinding
import com.github.togetherproject.button.module.help_screens.viewmodel.HomeViewModel
import com.github.togetherproject.button.utils.PermissionUltis.hasCallPermissions

class AskForHelpFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentAskForHelpBinding
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var num: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAskForHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btn153.setOnClickListener(this)
        binding.btn180.setOnClickListener(this)
        binding.btnServices.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_153 -> {
                num = "153"
                if (hasCallPermissions(requireContext())) viewModel.call(num, requireContext())
                else {
                    requestPermissions(
                        arrayOf(Manifest.permission.CALL_PHONE),
                        HomeFragment.REQUEST_CALL
                    )
                }
            }

            R.id.btn_180 -> {
                num = "180"
                if (hasCallPermissions(requireContext())) viewModel.call(num, requireContext())
                else {
                    requestPermissions(
                        arrayOf(Manifest.permission.CALL_PHONE),
                        HomeFragment.REQUEST_CALL
                    )
                }
            }

            R.id.btnServices -> {
                findNavController()
                    .navigate(
                        AskForHelpFragmentDirections
                            .actionAskForHelpFragmentToHelpServicesFragment()
                    )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == HomeFragment.REQUEST_CALL) viewModel.call(num, requireContext())
    }

}