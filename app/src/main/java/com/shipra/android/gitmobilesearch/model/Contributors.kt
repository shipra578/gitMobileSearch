package com.shipra.android.gitmobilesearch.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Contributors(

        var id: Int,

        @ColumnInfo(name = "contributor_name")
        @SerializedName("name")
        var name: String,


        @ColumnInfo(name = "avatar_url")
        @SerializedName("avatar_url")
        var avatar_url: String,


        var repo_url: String) {

}