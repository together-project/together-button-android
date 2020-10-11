package com.github.togetherproject.button.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Services (
    var name: String?,
    var address: String?,
    var phone: String?
): Parcelable