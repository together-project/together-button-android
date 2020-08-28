package com.github.togetherproject.button

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_together_bottom_sheet.view.*


class TogetherButton(private val context: Context) {

    private val view: View
    private val togetherDialog = BottomSheetDialog(context, R.style.TogetherBottomSheetDialog)

    init {
        val layoutInflater = LayoutInflater.from(context)
        view = layoutInflater.inflate(R.layout.fragment_together_bottom_sheet, null)
        setUpClickListeners()
        togetherDialog.behavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) togetherDialog.hide()
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })
    }

    fun show() {
        togetherDialog.behavior.peekHeight = 0
        togetherDialog.behavior.isHideable = true
        togetherDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        togetherDialog.setContentView(view)
        togetherDialog.show()
    }

    private fun setUpClickListeners() {
        view.imgBtnClose.setOnClickListener {
            togetherDialog.hide()
        }

        setUpCallClickListener()
        setUpContactClickListener()
    }

    private fun setUpCallClickListener() {
        view.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL)

            intent.data = Uri.parse("tel:${context.getString(R.string.emergency_number)}")
            context.startActivity(intent)
        }
    }

    private fun setUpContactClickListener() {
        view.btnContacts.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            context.startActivity(intent);
        }
    }
}