package com.shipra.android.gitmobilesearch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Repo(

        @PrimaryKey
        @ColumnInfo(name = "rId")
        var rId: Int,

        @Ignore var items: List<ItemsPojo>){


    constructor(): this(0,ArrayList<ItemsPojo>())

}