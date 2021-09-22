package com.walker.finalappdotaheros.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.walker.finalappdotaheros.models.Like

@Database(entities = [Like::class], version = 3, exportSchema = false)
abstract class LikeDatabase: RoomDatabase() {

    abstract fun likeDao() : LikeDao

    companion object {

        @Volatile
        private var INSTANCE: LikeDatabase? = null

        fun getDatabase(context: Context) : LikeDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        LikeDatabase::class.java,
                        "spent_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}