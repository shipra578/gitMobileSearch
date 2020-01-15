package com.shipra.android.gitmobilesearch.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(indices = [Index("contrId")] )
data class Contributors(

        @PrimaryKey
        @ColumnInfo(name = "contrId")
        @SerializedName("id")
        var id: Int,

        @ColumnInfo(name = "contributor_name")
        @SerializedName("login")
        var name: String,


        @ColumnInfo(name = "avatar_url")
        @SerializedName("avatar_url")
        var avatar_url: String,


        @ColumnInfo(name = "commit_count")
        @SerializedName("contributions")
        var commitCount: Int,

    /*    @ColumnInfo(name = "login")
        @SerializedName("login")
        var login: String,
*/

        @ColumnInfo(name = "repo_url")
        @SerializedName("repos_url")
        var repo_url: String) : Parcelable{
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString().toString(),
                parcel.readString().toString(),
                parcel.readInt(),
                parcel.readString().toString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(id)
                parcel.writeString(name)
                parcel.writeString(avatar_url)
                parcel.writeInt(commitCount)
                parcel.writeString(repo_url)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Contributors> {
                override fun createFromParcel(parcel: Parcel): Contributors {
                        return Contributors(parcel)
                }

                override fun newArray(size: Int): Array<Contributors?> {
                        return arrayOfNulls(size)
                }
        }

}