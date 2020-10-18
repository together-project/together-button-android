package com.github.togetherproject.button.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Services (
    val name: String,
    val address: String,
    val phone: String
): Parcelable