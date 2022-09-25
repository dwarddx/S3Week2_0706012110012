package com.example.w2s3_0706012110012.Model

import android.os.Parcel
import android.os.Parcelable

open class Animals (
    var name: String,
    var age: Int,
    var imageUri: String
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    )

    open fun interaction(): String {
        return "a";
    }

    open fun feed(): String {
        return "a";
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
        parcel.writeString(imageUri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Animals> {
        override fun createFromParcel(parcel: Parcel): Animals {
            return Animals(parcel)
        }

        override fun newArray(size: Int): Array<Animals?> {
            return arrayOfNulls(size)
        }
    }
}