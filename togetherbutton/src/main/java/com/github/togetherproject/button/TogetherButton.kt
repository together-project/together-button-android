package com.github.togetherproject.button

import androidx.appcompat.app.AppCompatActivity
import com.github.togetherproject.button.Fragments.TogetherButtonFragment
import com.github.togetherproject.button.Utils.PermissionUltis


class TogetherButton(private val context: AppCompatActivity) {

    fun show() {
        PermissionUltis.requestPermissions(context.applicationContext)
        val togetherButtonFragment = TogetherButtonFragment()
        togetherButtonFragment.show(context.supportFragmentManager, togetherButtonFragment.tag)
    }

}