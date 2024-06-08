package com.project.capstone.atongs_md.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.capstone.atongs_md.data.Result
import com.project.capstone.atongs_md.data.repository.ArticleRepository
import com.project.capstone.atongs_md.data.response.ArticlesItem

class ArticleViewModel(articleRepository: ArticleRepository): ViewModel(){

    val listArticle: LiveData<Result<List<ArticlesItem>>> = articleRepository.getListArticle()
}