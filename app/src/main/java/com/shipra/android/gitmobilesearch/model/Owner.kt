package com.shipra.android.gitmobilesearch.model

import com.google.gson.annotations.SerializedName

data class Owner(

        @SerializedName("id")
        var id: Int,

        @SerializedName("avatar_url")
        var avatar_url: String,

        @SerializedName("repos_url")
        var repos_url: String,

        @SerializedName("login")
        var login: String)
