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
import com.github.togetherproject.button.module.setup.TogetherButtonFragment
import com.github.togetherproject.button.utils.PermissionUltis.hasCallPermissions

class AskForHelpFragment : Fragment() {

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

        binding.setUpClickListeners()
    }

    private fun FragmentAskForHelpBinding.setUpClickListeners() {
        imgFinishFromCall.setOnClickListener{
            TogetherButtonFragment().dialog?.dismiss()
        }

        imgBackFromCall.setOnClickListener{
            findNavController().navigateUp()
        }

        btn153.setOnClickListener{
            makeCall("153")
        }

        btn180.setOnClickListener{
            makeCall("180")
        }

        btnServices.setOnClickListener{
            findNavController()
                .navigate(
                    AskForHelpFragmentDirections
                        .actionAskForHelpFragmentToHelpServicesFragment()
                )
        }
    }

    private fun makeCall (number: String) {
        num = number
        if (hasCallPermissions(requireContext())) viewModel.call(num, requireContext())
        else {
            requestPermissions(
                arrayOf(Manifest.permission.CALL_PHONE),
                HomeFragment.REQUEST_CALL
            )
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