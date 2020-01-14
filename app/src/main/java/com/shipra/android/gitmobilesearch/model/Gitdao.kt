package com.shipra.android.gitmobilesearch.model

import androidx.room.*

@Dao
interface GitDao {


    @Query("select * from itemspojo")
    fun loadAllRepositories(): List<ItemsPojo>?

    @Query("DELETE FROM itemspojo")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepositories(repo: Repositories?)


    @Insert
    fun insertHasRelationship(has: HasRelation?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContributors(t: Contributors)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItemsPojo(t: ItemsPojo)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHasRelation(t: HasRelation)

    @Query("SELECT * FROM ItemsPojo WHERE  id = :itId")
    fun checkIfItemPresent(itId: Int): Boolean


    @Query("SELECT * FROM Repositories WHERE  repoId = :reId")
    fun checkIfRepoPresent(reId: Int): Boolean

    @Query("SELECT * FROM relation WHERE  repoIdR = :primary")
    fun checkIfRelationPresent(primary: Int): Boolean


    @Query("SELECT * FROM Contributors WHERE  contId = :cId")
    fun checkIfContributorPresent(cId: Int): Boolean

    @Query("SELECT * FROM Repositories INNER JOIN  ItemsPojo ON Repositories.item_id = ItemsPojo.id  WHERE Repositories.item_id=:item")
    fun getRepositories(item: Int): List<Repositories>

    //@Query("SELECT ATTRIBUTES.* FROM ATTRIBUTES INNER JOIN PRODUCTS_ATTRIBUTES ON PRODUCTS_ATTRIBUTES._ATTRIBUTE_ID = ATTRIBUTES._ID INNER JOIN PRODUCTS ON PRODUCTS._ID = PRODUCTS_ATTRIBUTES._PRODUCT_ID WHERE PRODUCTS._ID = :productId ORDER BY PRODUCTS_ATTRIBUTES.DISPLAY_ORDERING ASC")
    @Query("SELECT Contributors.* FROM Contributors INNER JOIN  relation ON relation.ownerIdR = Contributors.contId INNER JOIN Repositories ON repoId = repoIdR WHERE Contributors.contId=:repositoryId")
    fun getAllContributors(repositoryId: Int) : List<Contributors>

}