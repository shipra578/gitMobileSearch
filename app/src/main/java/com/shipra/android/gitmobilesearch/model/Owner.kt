package com.shipra.android.gitmobilesearch.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Owner(

        @ColumnInfo(name = "owner_id")
        @SerializedName("id")
        var id: Int,

        @ColumnInfo(name = "avatar_url")
        @SerializedName("avatar_url")
        var avatar_url: String,

        @ColumnInfo(name = "repos_url")
        @SerializedName("repos_url")
        var repos_url: String,

        @ColumnInfo(name = "login")
        @SerializedName("login")
        var login: String){

}