package com.github.togetherproject.button.module.help_screens.view

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.togetherproject.button.R
import com.github.togetherproject.button.databinding.FragmentHelpServicesBinding
import com.github.togetherproject.button.model.Services
import com.github.togetherproject.button.module.help_screens.viewmodel.HomeViewModel
import com.github.togetherproject.button.module.setup.TogetherButtonFragment
import com.github.togetherproject.button.utils.PermissionUltis

class HelpServicesFragment: Fragment() {

    private lateinit var binding: FragmentHelpServicesBinding
    private val viewModel: HomeViewModel by viewModels()

    private var num = ""
    private var servicesAdapter = HelpServicesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHelpServicesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setUpClickListeners()
        viewModel.getServicesList()
        viewModel.viewLiveData.observe(this, Observer {state ->
            when (state) {
                is HomeViewModel.ViewState.LoadSuccess -> {
                    val services = state.services
                    setUpServices(services)
                }
                else -> {
                    Toast.makeText(
                        this@HelpServicesFragment.context,
                        getString(R.string.error_loading),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun FragmentHelpServicesBinding.setUpClickListeners() {
        imgFinishFromServices.setOnClickListener {
            TogetherButtonFragment().dialog?.dismiss()
        }

        imgBackFromServices.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setUpServices(services: List<Services>) {
        servicesAdapter.apply{
            setList(services)
            onServiceSelect = ::onServiceSelected
        }

        binding.recyclerServices.apply {
            layoutManager = LinearLayoutManager(this@HelpServicesFragment.context)
            adapter = servicesAdapter
        }
    }

    private fun onServiceSelected(services: Services) {
        num = services.phone
        if (PermissionUltis.hasCallPermissions(requireContext())) viewModel.call(num, requireContext())
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