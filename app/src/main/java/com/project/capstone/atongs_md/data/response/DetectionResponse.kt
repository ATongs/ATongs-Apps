package com.project.capstone.atongs_md.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class DetectionResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class Result(

	@field:SerializedName("probability")
	val probability: Double? = null,

	@field:SerializedName("suggestion")
	val suggestion: String? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("explanation")
	val explanation: String? = null
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: String? = null
) : Parcelable
