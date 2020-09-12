package com.github.togetherproject.button.dao

import android.os.Parcel
import android.os.Parcelable

class Services() : Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Services> {
        override fun createFromParcel(parcel: Parcel): Services {
            return Services(parcel)
        }

        override fun newArray(size: Int): Array<Services?> {
            return arrayOfNulls(size)
        }
    }

}