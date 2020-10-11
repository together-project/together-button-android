package com.github.togetherproject.button.module.setup

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.github.togetherproject.button.R
import com.github.togetherproject.button.databinding.FragmentTogetherBottomSheetBinding
import com.github.togetherproject.button.module.ask_for_help.AskForHelpFragmentDirections
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_together_bottom_sheet.*

class TogetherButtonFragment : BottomSheetDialogFragment(), View.OnClickListener {

    private var dialog: BottomSheetDialog? = null
    private lateinit var binding: FragmentTogetherBottomSheetBinding
    private lateinit var navController: NavController

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
        binding.btnBack.visibility = View.GONE
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog?.behavior?.peekHeight = 0
        dialog?.behavior?.isHideable = true
        dialog?.behavior?.state = BottomSheetBehavior.STATE_EXPANDED
        dialog?.behavior?.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) dialog?.hide()
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })

        return dialog!!
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_close -> {
                dialog?.hide()
            }

            R.id.btn_back -> {
                navController
                    .navigate(
                        AskForHelpFragmentDirections.actionAskForHelpFragmentToHomeFragment()
                    )
            }
        }
    }

}