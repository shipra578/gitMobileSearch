package com.shipra.android.gitmobilesearch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

/*
@Entity(tableName = "relation", primaryKeys = ["repoIdR", "contributorId"], foreignKeys = [

    ForeignKey(entity = Repositories::class, parentColumns = arrayOf("repoId"),childColumns = arrayOf("repoIdR"),onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = Contributors::class,parentColumns = arrayOf("contrId"),childColumns = arrayOf("contributorId"),onDelete = ForeignKey.CASCADE)
], indices = [Index("repoIdR", "contributorId")])
data class HasRelation(

        @ColumnInfo(name = "repoIdR")
        var repoIdR: Int,

        @ColumnInfo(name = "contributorId")
        var ownerIdR: Int

)*/
