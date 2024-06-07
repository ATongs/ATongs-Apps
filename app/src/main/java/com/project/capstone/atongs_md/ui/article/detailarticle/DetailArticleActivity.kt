package com.project.capstone.atongs_md.ui.article.detailarticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.bumptech.glide.Glide
import com.project.capstone.atongs_md.databinding.ActivityDetailArticleBinding

@Suppress("DEPRECATION")
class DetailArticleActivity : ComponentActivity() {

    private lateinit var binding: ActivityDetailArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra(EXTRA_TITLE)
        val description = intent.getStringExtra(EXTRA_DESCRIPTION)
        val imageUrl = intent.getStringExtra(EXTRA_IMAGE_URL)

        binding.tvTitle.text = title
        binding.tvDescription.text = description

        Glide.with(this)
            .load(imageUrl)
            .into(binding.ivImage)

        // Tambahkan logika lain jika diperlukan
    }

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_DESCRIPTION = "extra_description"
        const val EXTRA_IMAGE_URL = "extra_image_url"
    }
}
