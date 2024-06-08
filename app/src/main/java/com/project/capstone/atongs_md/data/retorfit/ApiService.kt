package com.project.capstone.atongs_md.data.retorfit

import com.project.capstone.atongs_md.data.response.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/everything")
    suspend fun getArticle(
        @Query("q") q: String,
        @Query("sortBy") sortBy: String,
        @Query("language") language: String,
        @Query("apiKey") apiKey: String
    ): ArticleResponse
}