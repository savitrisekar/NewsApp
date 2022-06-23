package com.savitrisekar.newsapp.ui.features.home.webview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.savitrisekar.newsapp.base.arch.BaseViewModelImpl
import com.savitrisekar.newsapp.base.model.Resource
import com.savitrisekar.newsapp.data.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WebNewsViewModel @Inject constructor(private val repository: WebNewsRepository) :
    BaseViewModelImpl(), WebNewsContract.ViewModel {

    private val newsLiveData = MutableLiveData<Resource<Article>>()
    val isFavorite by lazy { MutableLiveData<Int>(0) }

//    override fun getNewsResponse(): LiveData<Resource<Article>> = newsLiveData

    override fun insertNewsFavorite(article: Article) {
        viewModelScope.launch {
            if (isFavorite.value == 0) repository.insertNewsFavorite(article)
            else repository.deleteNewsFavorite(article)
        }
    }
}