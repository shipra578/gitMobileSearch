package com.shipra.android.gitmobilesearch.model

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
        var repositories: List<Repositories>) {

    constructor() : this(0, "", "", Owner(0, "", "", ""), 0, ArrayList<Repositories>())

}
