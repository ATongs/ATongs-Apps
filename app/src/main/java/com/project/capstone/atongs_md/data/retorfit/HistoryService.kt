package com.project.capstone.atongs_md.data.retorfit

import com.project.capstone.atongs_md.data.response.HistoryResponse
import retrofit2.http.GET

interface HistoryService {

    @GET("classify/histories")
    suspend fun getHistory(
    ): HistoryResponse
}