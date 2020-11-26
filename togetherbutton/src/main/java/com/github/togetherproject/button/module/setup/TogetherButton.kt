package com.github.togetherproject.button.module.setup

import androidx.appcompat.app.AppCompatActivity

class TogetherButton(private val context: AppCompatActivity) {

    fun show() {
        val togetherButtonFragment = TogetherButtonFragment()
        togetherButtonFragment.show(context.supportFragmentManager, togetherButtonFragment.tag)
    }

}