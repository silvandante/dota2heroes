package com.walker.finalappdotaheros.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walker.finalappdotaheros.data.LikeRepository
import com.walker.finalappdotaheros.models.Hero
import com.walker.finalappdotaheros.models.Like
import com.walker.finalappdotaheros.network.ApiRepository
import com.walker.finalappdotaheros.utils.Resource
import com.walker.finalappdotaheros.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HeroViewModel (
        private val repository: ApiRepository,
        private val likeRepository: LikeRepository
): ViewModel() {

    private val _hereosLiveData = MutableLiveData<Resource<List<Hero>>>()
    val heroesLiveData: LiveData<Resource<List<Hero>>> = _hereosLiveData

    fun getHeroesList() {
        viewModelScope.launch {
            _hereosLiveData.postValue(repository.getHeroes())
        }
    }

    fun likeThisHero(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            likeRepository.insertLike(Like(id))
        }
    }

    fun dislikeThisHero(heroId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            likeRepository.removeLike(heroId)
        }
    }

    fun isHeroLiked(id: Int): Boolean = runBlocking {
        return@runBlocking likeRepository.isHeroLiked(id) != null
    }

}