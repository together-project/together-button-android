package com.github.togetherproject.button.module.setup

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.github.togetherproject.button.R
import com.github.togetherproject.button.databinding.FragmentTogetherBottomSheetBinding
import com.github.togetherproject.button.module.help_screens.view.AskForHelpFragmentDirections
import com.github.togetherproject.button.module.help_screens.view.HelpServicesFragmentDirections
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TogetherButtonFragment : BottomSheetDialogFragment(), View.OnClickListener {

    private var dialog: BottomSheetDialog? = null
    private lateinit var binding: FragmentTogetherBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTogetherBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnClose.setOnClickListener(this)
        binding.btnBack.setOnClickListener(this)
        binding.btnBack.setOnClickListener(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog?.behavior?.apply {
            peekHeight = 0
            isHideable = true
            state = BottomSheetBehavior.STATE_EXPANDED
            addBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_COLLAPSED) dialog?.hide()
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }
            })
        }

        return dialog!!
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_close -> {
                dialog?.hide()
            }

            R.id.btn_back -> {
//                when (p0.findNavController().currentDestination?.id) {
//                    R.id.askForHelpFragment -> {
//                        p0.findNavController()
//                            .navigate(
//                                AskForHelpFragmentDirections
//                                    .actionAskForHelpFragmentToHomeFragment()
//                            )
//                    }
//
//                    R.id.helpServicesFragment -> {
//                        p0.findNavController()
//                            .navigate(
//                                HelpServicesFragmentDirections
//                                    .actionHelpServicesFragmentToAskForHelpFragment()
//                            )
//                    }
//                }
            }
        }
    }

}