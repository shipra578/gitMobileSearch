package com.shipra.android.gitmobilesearch.model

import androidx.room.*
import com.google.gson.annotations.SerializedName


@Entity(tableName = "Repositories" ,foreignKeys = [ForeignKey(entity = ItemsPojo::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("item_id"),
        onDelete = ForeignKey.CASCADE)], indices = [Index("item_id")])
data class Repositories(

        @PrimaryKey
        @ColumnInfo(name = "repoId")
        @SerializedName("id")
        var repoId: Int,

        @ColumnInfo(name = "item_id")
        var item_id: Int,

        @ColumnInfo(name = "name")
        @SerializedName("name")
        var name: String,

        @Ignore
        @SerializedName("owner")
        var owner: Owner,

        @ColumnInfo(name = "contributors_url")
        var contributors_url: String,

        @Ignore
        var contributors: List<Contributors>) {

    constructor() : this(0, 0, "", Owner(0,"","",""), "", ArrayList<Contributors>())
}




