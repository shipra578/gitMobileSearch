package com.shipra.android.gitmobilesearch.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class RepoPojo(

        @PrimaryKey
        @ColumnInfo(name = "id")
        @SerializedName("id")
        var id: String,

        @ColumnInfo(name = "name")
        @SerializedName("name")
        var name: String,

        @ColumnInfo(name = "full_name")
        @SerializedName("full_name")
        var full_name: String,

        @ColumnInfo(name = "url")
        @SerializedName("url")
        var url: String,

        @ColumnInfo(name = "fullname")
        @SerializedName("fullname")
        var watcher_count: Int,

        @ColumnInfo(name = "commitCount")
        @SerializedName("commitCount")
        var commitCount: Int,

        @ColumnInfo(name = "description")
        @SerializedName("description")
        var description: String,

        @ColumnInfo(name = "contributors")
        @SerializedName("contributors")
        var contributors: Contributors) {
}