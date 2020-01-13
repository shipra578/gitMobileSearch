package com.shipra.android.gitmobilesearch.model

import androidx.room.*

@Entity(tableName = "relation", foreignKeys = [

    ForeignKey(entity = Repositories::class, parentColumns = arrayOf("repoId"),childColumns = arrayOf("repoIdR"),onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = Contributors::class,parentColumns = arrayOf("contId"),childColumns = arrayOf("own"),onDelete = ForeignKey.CASCADE)
], indices = [Index("repoIdR","own")] )
data class HasRelation(

        @PrimaryKey(autoGenerate = true)
        var hasId: Int,

        @ColumnInfo(name = "repoIdR")
        var repoIdR : Int,

        @ColumnInfo(name = "own")
        var ownerIdR: Int

)