package com.project.capstone.atongs_md.data.response

import com.google.gson.annotations.SerializedName

data class ArticleResponse(

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<ArticlesItem?>? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class ArticlesItem(
    val id: String,
    val title: String?,
    val description: String?,
    val urlToImage: String?
)