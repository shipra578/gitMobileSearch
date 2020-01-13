package com.shipra.android.gitmobilesearch.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GitDao {


    @Query("select * from itemspojo")
    fun loadAllRepositories(): List<ItemsPojo>?

/*    @Insert
    fun insertRepo(repo: Repo?)


    @Delete
    fun deleteRepo(repo: Repo?)

    @Query("DELETE FROM itemspojo")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateRepo(repo: Repo?)*/


    @Insert
    fun insertRepositories(repo: Repositories?)


    @Insert
    fun insertHasRelationship(has: HasRelation?)

    @Insert
    fun insertContributors(t: Contributors)

    @Insert
    fun insertItemsPojo(t: ItemsPojo)


    @Insert
    fun insertHasRelation(t: HasRelation)


}