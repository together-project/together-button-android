package com.github.togetherproject.button.utils

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment

fun Fragment.call(num: String) {
    val intent = Intent(Intent.ACTION_CALL)
    intent.data = Uri.parse("tel:${num}")
    context?.startActivity(intent)
}

fun String.bold(): String {
    return this
}

fun String.toColor(): String {
    return this
}

fun String.underlined(): String {
    return this
}