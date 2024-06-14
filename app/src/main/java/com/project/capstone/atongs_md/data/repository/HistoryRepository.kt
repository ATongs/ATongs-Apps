package com.project.capstone.atongs_md.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.project.capstone.atongs_md.data.Result
import com.project.capstone.atongs_md.data.response.DataItem
import com.project.capstone.atongs_md.data.retorfit.HistoryService

class HistoryRepository(
    private val apiService: HistoryService
) {

    fun getListHistory(): LiveData<Result<List<DataItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getHistory()
            Log.d("HistoryRepository", "Response: $response")
            val data = response.data
            emit(Result.Success(data))
        } catch (e: Exception) {
            Log.e("HistoryRepository", "Error fetching history: ${e.message}", e)
            emit(Result.Error(e))
        }
    }

    companion object {
        @Volatile
        private var instance: HistoryRepository? = null

        fun getInstance(apiService: HistoryService): HistoryRepository =
            instance ?: synchronized(this) {
                instance ?: HistoryRepository(apiService).also { instance = it }
            }
    }
}
