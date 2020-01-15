package com.shipra.android.gitmobilesearch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Repo(

        @PrimaryKey
        @ColumnInfo(name = "searchText")
        var searchText: String,

        @Ignore var items: List<ItemsPojo>){


    constructor(): this("",ArrayList<ItemsPojo>())

}