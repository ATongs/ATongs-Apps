package com.project.capstone.atongs_md.ui.detection

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.capstone.atongs_md.data.response.DetectionResponse
import com.project.capstone.atongs_md.databinding.ActivityDetectionResultBinding
import java.io.File

@Suppress("DEPRECATION")
class DetectionResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetectionResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetectionResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataResult()
        setPreviewImage() // Call method to set image
    }

    private fun getDataResult() {
        val dataResultDetection = intent.getParcelableExtra<DetectionResponse>(DETECTION_RESULT) as DetectionResponse
        setDataResultView(dataResultDetection)
    }

    private fun setDataResultView(dataResult: DetectionResponse) {
        binding.apply {
            tvDetection.text = dataResult.data?.result?.label
            tvDescAccuracy.text = dataResult.data?.result?.probability.toString()
            tvDate.text = dataResult.data?.createdAt
            tvDescResult.text = dataResult.data?.result?.message
        }
    }

    private fun setPreviewImage() {
        val imagePath = intent.getStringExtra(IMAGE_PATH)
        if (!imagePath.isNullOrEmpty()) {
            val imageUri = Uri.fromFile(File(imagePath))
            if (File(imagePath).exists()) {
                binding.imgResult.setImageURI(imageUri) // Assuming ivPreviewImage is the ImageView in DetectionResultActivity
            } else {
                Toast.makeText(this, "File gambar tidak ada", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Jalur gambar tidak valid", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val DETECTION_RESULT = "detection_result"
        const val IMAGE_PATH = "image_absolute_path"
    }
}