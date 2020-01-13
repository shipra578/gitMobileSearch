package com.shipra.android.gitmobilesearch.model

import android.app.Application
import androidx.room.*

@TypeConverters(OwnerTypeConverter::class)
@Database(entities = [ItemsPojo::class, Repositories::class, Contributors::class, HasRelation::class, Repo::class], version = 1, exportSchema = false)
abstract class GitProjectDatabase constructor(app: Application) : RoomDatabase() {

    constructor() : this(app = Application()) {}

    companion object {
        @JvmField
        val DATABASE_NAME = "GitProjectdataBase"

        @JvmField
        var mInstance: GitProjectDatabase? = null

        @JvmField
        var LOCK = Object()


        fun getInstance(app: Application?): GitProjectDatabase? {
            if (mInstance == null) {
                synchronized(LOCK) {
                    if (mInstance == null) {
                        if (app != null && app.applicationContext != null) {
                            mInstance = Room.databaseBuilder(app.applicationContext, GitProjectDatabase::class.java, DATABASE_NAME).allowMainThreadQueries().build()
                        }
                    }
                }
            }
            return mInstance
        }
    }

    abstract fun gitDatabaseDao(): GitDao

}