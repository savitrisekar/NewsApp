package com.savitrisekar.newsapp.ui.features.home.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.savitrisekar.newsapp.base.arch.BaseViewModelImpl
import com.savitrisekar.newsapp.base.model.Resource
import com.savitrisekar.newsapp.data.model.Article
import com.savitrisekar.newsapp.data.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : BaseViewModelImpl(), HomeListContract.ViewModel {

    private val recipeLiveData = MutableLiveData<Resource<List<Article>>>()

    private val searchLiveData = MutableLiveData<Resource<List<Article>>>()

    private val categoryLiveData = MutableLiveData<Resource<List<Category>>>()

    override fun getRecipeListLiveData(): LiveData<Resource<List<Article>>> {
        return recipeLiveData
    }

    fun getCategoryLiveData(): LiveData<Resource<List<Category>>> {
        return categoryLiveData
    }

    override fun getAllNews(category: String) {
        recipeLiveData.value = Resource.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getAllNews(category)
                viewModelScope.launch(Dispatchers.Main) {
                    recipeLiveData.value = response.articles?.let { Resource.Success(it) }!!
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    recipeLiveData.value = Resource.Error(e.localizedMessage.orEmpty())
                }
            }
        }
    }

    override fun searchNews(query: String) {
        recipeLiveData.value = Resource.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.searchNews(query)
                viewModelScope.launch(Dispatchers.Main) {
                    recipeLiveData.value = response.articles?.let { Resource.Success(it) }!!
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    recipeLiveData.value = Resource.Error(e.localizedMessage.orEmpty())
                }
            }
        }
    }
}