package com.github.togetherproject.button

import android.content.Context
import android.content.Intent

class TogetherButton(private val context: Context) {

    fun startTogether() {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

}