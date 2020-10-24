package com.github.togetherproject.button.module.help_screens.view

import android.graphics.Typeface
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.togetherproject.button.R
import com.github.togetherproject.button.databinding.ItemServiceBinding
import com.github.togetherproject.button.model.Services

class HelpServicesViewHolder(
    private val view: ItemServiceBinding
): RecyclerView.ViewHolder(view.root) {

    fun bind(service: Services) {
        val name = "${service.name}: "
        val nameSpannable = SpannableString(name)
        nameSpannable.setSpan(StyleSpan(Typeface.BOLD), 0, name.length, 0)

        val phone = " ${service.phone}"
        val phoneSpannable = SpannableString(phone)
        phoneSpannable.apply {
            setSpan(
                ForegroundColorSpan(view.root.resources.getColor(R.color.colorPurple)),
                0,
                phone.length,
                0
            )
            setSpan(UnderlineSpan(), 0, phone.length, 0)
        }

        val builder = SpannableStringBuilder()
        builder.apply {
            append(nameSpannable)
            append(" ${service.address}.")
            append(phoneSpannable)
        }

        view.txtPlaceAllInfos.setText(builder, TextView.BufferType.SPANNABLE);
    }

}