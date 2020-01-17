package com.shipra.android.gitmobilesearch.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import io.reactivex.annotations.Nullable

@Entity(tableName = "ItemsPojo", indices = [Index("id","name")])
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

        @ColumnInfo(name = "description")
        @SerializedName("description")
        @Nullable
        @androidx.annotation.Nullable
        @org.jetbrains.annotations.Nullable
        @javax.annotation.Nullable
        var description: String?,


        @ColumnInfo(name = "html_url")
        @SerializedName("html_url")
        var html_url: String,

        @Ignore
        var repositories: List<Repositories>) : Parcelable{

        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readParcelable(Owner::class.java.classLoader)!!,
                parcel.readInt(),
                parcel.readString(),
                parcel.readString()!!,
                parcel.createTypedArrayList(Repositories)!!) {
        }

        constructor() : this(0, "", "", Owner(0, "", "", ""), 0,"","" ,ArrayList<Repositories>())

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(id)
                parcel.writeString(name)
                parcel.writeString(full_name)
                parcel.writeParcelable(owner, flags)
                parcel.writeInt(watcher_count)
                parcel.writeString(description)
                parcel.writeString(html_url)
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
