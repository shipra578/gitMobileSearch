package com.shipra.android.gitmobilesearch.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Owner(

        @ColumnInfo(name = "contributor_name")
        @SerializedName("name")
        var id: Int,


        @ColumnInfo(name = "contributor_name")
        @SerializedName("name")
        var avatar_url: String,


        @ColumnInfo(name = "contributor_name")
        @SerializedName("name")
        var repo_url: String){

}