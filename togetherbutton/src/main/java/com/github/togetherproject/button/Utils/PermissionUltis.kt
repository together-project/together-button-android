package com.github.togetherproject.button.Utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

object PermissionUltis {

    fun requestPermissions(context: Context) {
        if (!hasPermissions(context)) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.CALL_PHONE),
                1
            )
        }
    }

    fun hasPermissions(context: Context) =
        context.checkCallingOrSelfPermission(Manifest.permission.CALL_PHONE) ==
                PackageManager.PERMISSION_GRANTED

}