package com.project.capstone.atongs_md.data.retorfit

import com.project.capstone.atongs_md.data.response.DetectionResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface DetectionApiService {
    @Multipart
    @POST("classify")
    suspend fun postDetectionDisease(
        @Part image: MultipartBody.Part
    ): DetectionResponse
}
