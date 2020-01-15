package com.shipra.android.gitmobilesearch.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Owner(

        @SerializedName("id")
        var id: Int,

        @SerializedName("avatar_url")
        var avatar_url: String,

        @SerializedName("repos_url")
        var repos_url: String,

        @SerializedName("login")
        var login: String): Parcelable{
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(id)
                parcel.writeString(avatar_url)
                parcel.writeString(repos_url)
                parcel.writeString(login)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Owner> {
                override fun createFromParcel(parcel: Parcel): Owner {
                        return Owner(parcel)
                }

                override fun newArray(size: Int): Array<Owner?> {
                        return arrayOfNulls(size)
                }
        }

}
