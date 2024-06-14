package com.project.capstone.atongs_md.ui.detection

import android.net.Uri
import android.os.Bundle
import android.util.Log
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
        setPreviewImage()
    }

    private fun getDataResult() {
        val dataResultDetection = intent.getParcelableExtra<DetectionResponse>(DETECTION_RESULT)
        if (dataResultDetection != null) {
            Log.d("DetectionResultActivity", "Data Result: $dataResultDetection")
            setDataResultView(dataResultDetection)
        } else {
            Toast.makeText(this, "Data hasil deteksi tidak ditemukan", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setDataResultView(dataResult: DetectionResponse) {
        binding.apply {
            tvDetection.text = dataResult.data?.result?.label ?: "N/A"
            tvDescAccuracy.text = dataResult.data?.result?.probability?.toString() ?: "N/A"
            tvDescExplanation.text = dataResult.data?.result?.explanation ?: "Tidak ada penjelasan"
            tvDescSuggestion.text = dataResult.data?.result?.suggestion ?: "Tidak ada saran"
            tvMessageResult.text = dataResult.message ?: "Tidak ada pesan"
        }
    }

    private fun setPreviewImage() {
        val imagePath = intent.getStringExtra(IMAGE_PATH)
        Log.d("DetectionResultActivity", "Image Path: $imagePath")
        if (!imagePath.isNullOrEmpty()) {
            val imageFile = File(imagePath)
            if (imageFile.exists()) {
                val imageUri = Uri.fromFile(imageFile)
                binding.imgResult.setImageURI(imageUri)
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
