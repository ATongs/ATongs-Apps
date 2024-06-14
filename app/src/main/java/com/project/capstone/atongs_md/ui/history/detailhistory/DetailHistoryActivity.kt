package com.project.capstone.atongs_md.ui.history.detailhistory

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.bumptech.glide.Glide
import com.project.capstone.atongs_md.data.response.DataItem
import com.project.capstone.atongs_md.data.response.ResultHistory
import com.project.capstone.atongs_md.databinding.ActivityDetailHistoryBinding

@Suppress("DEPRECATION")
class DetailHistoryActivity : ComponentActivity() {

    private lateinit var binding: ActivityDetailHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra(EXTRA_TITLE)
        val imageUrl = intent.getStringExtra(EXTRA_IMAGE_URL)
        val dataItem = intent.getParcelableExtra<DataItem>(EXTRA_DATA_ITEM)

        binding.tvDetection.text = title

        Glide.with(this)
            .load(imageUrl)
            .into(binding.imgResult)

        if (dataItem?.result != null) {
            setDataResultView(dataItem.result, dataItem.result.message)
        }
    }

    private fun setDataResultView(resultHistory: ResultHistory, message: String?) {
        binding.apply {
            tvDetection.text = resultHistory.label ?: "N/A"
            tvDescAccuracy.text = resultHistory.probability?.toString() ?: "N/A"
            tvDescExplanation.text = resultHistory.explanation ?: "Tidak ada penjelasan"
            tvDescSuggestion.text = resultHistory.suggestion ?: "Tidak ada saran"
            tvMessageResult.text = message ?: "Tidak ada pesan"
        }
    }

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_IMAGE_URL = "extra_image_url"
        const val EXTRA_DATA_ITEM = "extra_data_item"  // Added key for DataItem
    }
}
