package com.project.capstone.atongs_md.data.repository

import android.util.Log
import com.project.capstone.atongs_md.data.retorfit.DetectionApiService
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import com.project.capstone.atongs_md.data.Result
import com.project.capstone.atongs_md.data.response.DetectionResponse
import com.project.capstone.atongs_md.util.reduceFileImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType

class DetectionRepository private constructor(
    private val detectionApiService: DetectionApiService
) {
    fun postDetectionDisease(
        imageFile: File
    ): Flow<Result<DetectionResponse>> = flow {
        emit(Result.Loading)
        try {
            val reducedFile = reduceFileImage(imageFile)
            val requestImageFile = reducedFile.asRequestBody("image/jpeg".toMediaType())
            val imageMultipart: MultipartBody.Part =
                MultipartBody.Part.createFormData("image", imageFile.name, requestImageFile)
            val response = detectionApiService.postDetectionDisease(
                imageMultipart
            )
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d("DetectionRepository", "postDetectionDisease: ${e.message}")
            emit(Result.Error(e))
        }
    }

    companion object {
        @Volatile
        private var instance: DetectionRepository? = null
        fun getInstance(
            apiService: DetectionApiService
        ): DetectionRepository = instance ?: synchronized(this) {
            instance ?: DetectionRepository(apiService).also { instance = it }
        }
    }
}
