package com.walker.finalappdotaheros.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.walker.finalappdotaheros.models.Like

@Dao
interface LikeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLike(likeData: Like)

    @Query("DELETE FROM like_table WHERE heroId = :heroId")
    suspend fun removeLike(heroId: Int)

    @Query("SELECT * FROM like_table WHERE heroId = :heroId LIMIT 1")
    suspend fun isHeroLiked(heroId: Int): Like?
}