package com.shipra.android.gitmobilesearch.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName


@Entity(tableName = "Repositories" ,foreignKeys = [ForeignKey(entity = ItemsPojo::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("item_id"),
        onDelete = ForeignKey.CASCADE)], indices = [Index("item_id","repoId")])
data class Repositories(

        @PrimaryKey
        @ColumnInfo(name = "repoId")
        @SerializedName("id")
        var repoId: Int,

        @ColumnInfo(name = "item_id")
        var item_id: Int,

        @ColumnInfo(name = "name")
        @SerializedName("name")
        var name: String ="",

        @Ignore
        @SerializedName("owner")
        var owner: Owner,

        @ColumnInfo(name = "contributors_url")
        var contributors_url: String,

        @Ignore
        var contributors: List<Contributors>) : Parcelable {

        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readInt(),
                parcel.readString()!!,
                parcel.readParcelable<Owner>(ClassLoader.getSystemClassLoader())!!,
                parcel.readString()!!,
                parcel.createTypedArrayList(Contributors) as List<Contributors>) {
        }

        constructor() : this(0, 0, "", Owner(0,"","",""), "", ArrayList<Contributors>())

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(repoId)
                parcel.writeInt(item_id)
                parcel.writeString(name)
                parcel.writeParcelable(owner, Parcelable.PARCELABLE_WRITE_RETURN_VALUE)
                parcel.writeString(contributors_url)
                parcel.writeTypedList(contributors)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Repositories> {
                override fun createFromParcel(parcel: Parcel): Repositories {
                        return Repositories(parcel)
                }

                override fun newArray(size: Int): Array<Repositories?> {
                        return arrayOfNulls(size)
                }
        }
}




