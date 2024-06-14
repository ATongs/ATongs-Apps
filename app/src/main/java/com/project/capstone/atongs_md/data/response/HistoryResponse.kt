package com.project.capstone.atongs_md.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class HistoryResponse(

	@field:SerializedName("data")
	val data: List<DataItem> = emptyList(),

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class DataItem(

	@field:SerializedName("result")
	val result: ResultHistory? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("id")
	val id: String? = null
) : Parcelable

@Parcelize
data class ResultHistory(

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
