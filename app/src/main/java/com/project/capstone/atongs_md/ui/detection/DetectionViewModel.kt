package com.project.capstone.atongs_md.ui.detection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.capstone.atongs_md.data.repository.DetectionRepository
import java.io.File

class DetectionViewModel(private val detectionRepository: DetectionRepository) : ViewModel() {

    fun postDetectionDisease(imageFile: File) =
        detectionRepository.postDetectionDisease(imageFile).asLiveData()
}
