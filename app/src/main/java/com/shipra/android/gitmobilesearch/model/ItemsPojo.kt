package com.shipra.android.gitmobilesearch.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ItemsPojo", indices = [Index("id")])
data class ItemsPojo(

        @PrimaryKey
        @ColumnInfo(name = "id")
        @SerializedName("id")
        var id: Int,

        @ColumnInfo(name = "name")
        @SerializedName("name")
        var name: String,

        @ColumnInfo(name = "full_name")
        @SerializedName("full_name")
        var full_name: String,

        @TypeConverters(Owner::class)
        @SerializedName("owner")
        var owner: Owner,

        @ColumnInfo(name = "watcher_count")
        @SerializedName("watchers_count")
        var watcher_count: Int,

        /*  @ColumnInfo(name = "contributors_url")
          @SerializedName("contributors_url")
          var contributors_url: String,*/

        @Ignore
        var repositories: List<Repositories>) : Parcelable{

        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readParcelable(Owner::class.java.classLoader)!!,
                parcel.readInt(),
                parcel.createTypedArrayList(Repositories)!!) {
        }

        constructor() : this(0, "", "", Owner(0, "", "", ""), 0, ArrayList<Repositories>())

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(id)
                parcel.writeString(name)
                parcel.writeString(full_name)
                parcel.writeParcelable(owner, flags)
                parcel.writeInt(watcher_count)
                parcel.writeTypedList(repositories)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<ItemsPojo> {
                override fun createFromParcel(parcel: Parcel): ItemsPojo {
                        return ItemsPojo(parcel)
                }

                override fun newArray(size: Int): Array<ItemsPojo?> {
                        return arrayOfNulls(size)
                }
        }

}
