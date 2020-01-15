package com.shipra.android.gitmobilesearch.model

import androidx.room.*

@Entity(tableName = "ContributorRepoTable",indices = [Index("contributorIdRelation","repoIdRelation")],foreignKeys = [ForeignKey(entity = Contributors::class,parentColumns = arrayOf("contrId"),childColumns = arrayOf("contributorIdRelation"),onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = Repositories::class,parentColumns = arrayOf("repoId"),childColumns = arrayOf("repoIdRelation"),onDelete = ForeignKey.CASCADE)
] )
data class ContributorRepoRelation(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "contRepoId")
    var _id: Long = 0,

    @ColumnInfo(name = "contributorIdRelation")
    var contributorIdRelation: Int = 0,

    @ColumnInfo(name = "repoIdRelation")
    var repoIdRelation: Int = 0){
}