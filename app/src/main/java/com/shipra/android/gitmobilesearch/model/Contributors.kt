package com.shipra.android.gitmobilesearch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(indices = [Index("contId")] )
data class Contributors(

        @PrimaryKey
        @SerializedName("id")
        @ColumnInfo(name = "contId")
        var id: Int,

        @ColumnInfo(name = "contributor_name")
        @SerializedName("name")
        var name: String,


        @ColumnInfo(name = "avatar_url")
        @SerializedName("avatar_url")
        var avatar_url: String,


        @ColumnInfo(name = "commit_count")
        @SerializedName("contributions")
        var commitCount: Int,

        @ColumnInfo(name = "login")
        @SerializedName("login")
        var login: String,


        @ColumnInfo(name = "repo_url")
        @SerializedName("repo_url")
        var repo_url: String)