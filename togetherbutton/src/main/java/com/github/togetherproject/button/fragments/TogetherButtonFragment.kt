package com.github.togetherproject.button.fragments

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.togetherproject.button.R
import com.github.togetherproject.button.utils.PermissionUltis
import com.github.togetherproject.button.databinding.FragmentTogetherBottomSheetBinding
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
        binding = FragmentTogetherBottomSheetBinding.inflate(inflater)

        val fragmentManager = childFragmentManager
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, HomeFragment(fragmentManager)).commit()
        binding.headerLayout.imgBtnClose.setOnClickListener(this)

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

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.imgBtnClose -> {
                dialog?.hide()
            }
        }
    }

}