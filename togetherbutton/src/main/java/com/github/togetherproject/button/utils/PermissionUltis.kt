package com.github.togetherproject.button.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.github.togetherproject.button.R

object PermissionUltis {

    fun hasCallPermissions(context: Context) =
        context.checkCallingOrSelfPermission(Manifest.permission.CALL_PHONE) ==
    PackageManager.PERMISSION_GRANTED

    fun hasReadPermissions(context: Context) =
        context.checkCallingOrSelfPermission(Manifest.permission.READ_CONTACTS) ==
                PackageManager.PERMISSION_GRANTED

    fun handleDidntGrantPermissions(context: Context) {
        Toast.makeText(
            context,
            context.getString(R.string.permissions_denied_text),
            Toast.LENGTH_SHORT
        ).show()
    }
}