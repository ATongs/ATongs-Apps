package com.project.capstone.atongs_md.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.project.capstone.atongs_md.BuildConfig
import com.project.capstone.atongs_md.data.response.ArticlesItem
import com.project.capstone.atongs_md.data.retorfit.ApiService
import com.project.capstone.atongs_md.data.Result

class ArticleRepository(
    private val apiService: ApiService
) {

    fun getListArticle(): LiveData<Result<List<ArticlesItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getArticle("environment", "relevancy", "en", BuildConfig.API_KEY)
            if (response.status == "ok") {
                emit(Result.Success(response.articles?.filterNotNull() ?: emptyList()))
            } else {
                emit(Result.Error(Exception("Error fetching articles: ${response.status}")))
            }

        } catch (e: Exception) {
            Log.d("ArticleRepository", "getListArticle: ${e.message.toString()}")
            emit(Result.Error(e))
        }
    }

    companion object {
        @Volatile
        private var instance: ArticleRepository? = null
        fun getInstance(
            apiService: ApiService
        ): ArticleRepository = instance ?: synchronized(this) {
            instance ?: ArticleRepository(apiService)
        }.also { instance = it }
    }
}
