package com.project.capstone.atongs_md.data.di

import com.project.capstone.atongs_md.data.repository.ArticleRepository
import com.project.capstone.atongs_md.data.repository.DetectionRepository
import com.project.capstone.atongs_md.data.retorfit.ApiConfig

object Injection {
    fun provideRepository(): ArticleRepository {
        val apiService = ApiConfig.getApiService()
        return ArticleRepository.getInstance(apiService)
    }
    fun provideDetectionRepository(): DetectionRepository {
        val apiService = ApiConfig.getDetectionApiService()
        return DetectionRepository.getInstance(apiService)
    }
}