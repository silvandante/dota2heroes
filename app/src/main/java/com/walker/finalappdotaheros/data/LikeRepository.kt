package com.walker.finalappdotaheros.data

import androidx.lifecycle.LiveData
import com.walker.finalappdotaheros.models.Like
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LikeRepository(
        val likeDao: LikeDao
) {
    suspend fun insertLike(like: Like) {
        likeDao.insertLike(like)
    }

    suspend fun removeLike(heroId: Int) {
        likeDao.removeLike(heroId)
    }

    suspend fun isHeroLiked(id: Int): Like? =
            withContext(Dispatchers.IO) { likeDao.isHeroLiked(id) }


}