package com.github.togetherproject.button

import androidx.appcompat.app.AppCompatActivity
import com.github.togetherproject.button.fragments.TogetherButtonFragment

class TogetherButton(private val context: AppCompatActivity) {

    fun show() {
        val togetherButtonFragment = TogetherButtonFragment()
        togetherButtonFragment.show(context.supportFragmentManager, togetherButtonFragment.tag)
    }

}