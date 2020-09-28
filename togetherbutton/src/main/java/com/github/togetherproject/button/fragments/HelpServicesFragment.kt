package com.github.togetherproject.button.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.github.togetherproject.button.databinding.FragmentAskForHelpBinding
import com.github.togetherproject.button.databinding.FragmentHelpServicesBinding

class HelpServicesFragment(val manager: FragmentManager) : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentHelpServicesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHelpServicesBinding.inflate(inflater)

        return binding.root
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}