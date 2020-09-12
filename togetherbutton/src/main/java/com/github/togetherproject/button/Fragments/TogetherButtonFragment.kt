package com.github.togetherproject.button.Fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.togetherproject.button.R
import com.github.togetherproject.button.databinding.FragmentTogetherBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TogetherButtonFragment : BottomSheetDialogFragment() {

    private var dialog: BottomSheetDialog? = null
    private lateinit var binding: FragmentTogetherBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTogetherBottomSheetBinding.inflate(inflater)

        val fragmentManager = childFragmentManager
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, HomeFragment()).commit()
        binding.headerLayout.imgBtnClose.setOnClickListener {
            dialog?.hide()
        }

        return binding.root
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
}