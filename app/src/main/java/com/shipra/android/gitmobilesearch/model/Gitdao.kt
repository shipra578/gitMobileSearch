package com.shipra.android.gitmobilesearch.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GitDao {


    @Query("select * from itemspojo ORDER BY watcher_count DESC")
    fun loadAllItemsRepo(): List<ItemsPojo>

    @Query("DELETE FROM itemspojo")
    fun deleteAll()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepo(repo: Repo?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepositories(repo: Repositories?): Long


 /*   @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHasRelationship(has: HasRelation?)*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContRepo(has: ContributorRepoRelation?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContributors(t: Contributors)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItemsPojo(t: ItemsPojo)

/*
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHasRelation(t: HasRelation)*/


    @Query("SELECT * FROM Repo WHERE  searchText = :text")
    fun checkIfRepoExists(text: String): Repo

    @Query("SELECT * FROM ItemsPojo WHERE  id = :itId")
    fun checkIfItemPresent(itId: Int): ItemsPojo

    @Query("SELECT * FROM Repositories")
    fun selectAllRepositories(): List<Repositories>

    @Query("SELECT * FROM Repositories WHERE  repoId = :reId")
    fun checkIfRepoPresent(reId: Int): Repositories

    @Query("SELECT * FROM contributorrepotable WHERE  contRepoId = :primary")
    fun checkIfRelationPresent(primary: Int): ContributorRepoRelation


    @Query("SELECT * FROM Contributors WHERE  contrId = :cId")
    fun checkIfContributorPresent(cId: Int): Contributors

    @Query("SELECT * FROM Repositories INNER JOIN  ItemsPojo ON Repositories.item_id = ItemsPojo.id  WHERE Repositories.item_id=:item")
    fun getRepositories(item: Int): List<Repositories>


    @Query("SELECT Contributors.* FROM Contributors INNER JOIN ContributorRepoTable ON ContributorRepoTable.contributorIdRelation = Contributors.contrId INNER JOIN Repositories ON Repositories.repoId = ContributorRepoTable.repoIdRelation WHERE Repositories.repoId = :repositoryId")
    fun getAllContributors1(repositoryId: Int): List<Contributors>

    @Query("SELECT * FROM repositories WHERE item_id =:itemId1")
    fun getRepositoriesFromItemId(itemId1: Int): List<Repositories>

   /* //@Query("SELECT ATTRIBUTES.* FROM ATTRIBUTES INNER JOIN PRODUCTS_ATTRIBUTES ON PRODUCTS_ATTRIBUTES._ATTRIBUTE_ID = ATTRIBUTES._ID INNER JOIN PRODUCTS ON PRODUCTS._ID = PRODUCTS_ATTRIBUTES._PRODUCT_ID WHERE PRODUCTS._ID = :productId ORDER BY PRODUCTS_ATTRIBUTES.DISPLAY_ORDERING ASC")
    @Query("SELECT Contributors.* FROM Contributors INNER JOIN  contributorrepotable ON contributorrepotable.contributorIdRelation = Contributors.contrId INNER JOIN Repositories ON repoId = contributorIdRelation WHERE Contributors.contrId=:repositoryId")
    fun getAllContributors(repositoryId: Int): List<Contributors>*/

}