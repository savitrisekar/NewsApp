package com.savitrisekar.newsapp.ui.features.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.savitrisekar.newsapp.base.arch.BaseViewModelImpl
import com.savitrisekar.newsapp.base.model.Resource
import com.savitrisekar.newsapp.data.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsFavoriteViewModel @Inject constructor(private val repository: NewsFavoriteRepository) :
    BaseViewModelImpl(), NewsFavoriteContract.ViewModel {

    private val newsLiveData = MutableLiveData<Resource<List<Article>>>()

    override fun getNewsListLiveData(): LiveData<Resource<List<Article>>> = newsLiveData

    override fun getAllNewsFavorite() {
        newsLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newsFavorite = repository.getAllNewsFavorite()
                viewModelScope.launch(Dispatchers.Main) {
                    newsLiveData.value = Resource.Success(newsFavorite)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    newsLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    override fun deleteNewsFavorite(article: Article) {
        viewModelScope.launch {
            repository.deleteNewsFavorite(article)
        }
    }
}