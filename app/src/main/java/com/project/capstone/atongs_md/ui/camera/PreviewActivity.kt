package com.project.capstone.atongs_md.ui.camera

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.project.capstone.atongs_md.databinding.ActivityPreviewBinding
import com.project.capstone.atongs_md.ui.detection.DetectionResultActivity
import com.project.capstone.atongs_md.data.Result
import com.project.capstone.atongs_md.ui.detection.DetectionViewModel
import com.project.capstone.atongs_md.ui.detection.DetectionViewModelFactory
import java.io.File

class PreviewActivity : AppCompatActivity() {

    private var isDetection = true
    private var imageFile: File? = null

    private lateinit var binding: ActivityPreviewBinding

    private val detectionViewModel: DetectionViewModel by viewModels {
        DetectionViewModelFactory.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCondition()
        setPreviewImage()
        setAction()
    }

    private fun getCondition() {
        isDetection = intent.getBooleanExtra(IS_DETECTION, false)
    }

    private fun setAction() {
        binding.btnUpload.setOnClickListener {
            if (isDetection) {
                postDetection()
            } else {
                uploadProfilePicture()
            }
        }
    }

    private fun postDetection() {
        if (imageFile != null) {
            detectionViewModel.postDetectionDisease(imageFile!!)
                .observe(this) { result ->
                    when (result) {
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val dataResult = result.data
                            val intent = Intent(this@PreviewActivity, DetectionResultActivity::class.java).apply {
                                putExtra(DetectionResultActivity.DETECTION_RESULT, dataResult)
                                putExtra(DetectionResultActivity.IMAGE_PATH, imageFile!!.absolutePath) // Pass image path
                            }
                            startActivity(intent)
                            finish()
                        }
                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "Gagal mendeteksi sampah", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        } else {
            Toast.makeText(this, "File gambar tidak ada", Toast.LENGTH_SHORT).show()
        }
    }


    private fun uploadProfilePicture() {
        // Implementasikan fungsionalitas upload foto profil di sini
        Toast.makeText(this, "Fungsionalitas upload foto profil belum diimplementasikan", Toast.LENGTH_SHORT).show()
    }

    private fun setPreviewImage() {
        val imagePath = intent.getStringExtra(IMAGE_PATH)
        if (!imagePath.isNullOrEmpty()) {
            val imageUri = Uri.parse(imagePath)
            imageFile = imageUri.path?.let { File(it) }
            if (imageFile?.exists() == true) {
                binding.ivPreviewImage.setImageURI(imageUri)
            } else {
                Toast.makeText(this, "File gambar tidak ada", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Jalur gambar tidak valid", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val IS_DETECTION = "is_detection"
        const val IMAGE_PATH = "image_absolute_path"
    }
}
