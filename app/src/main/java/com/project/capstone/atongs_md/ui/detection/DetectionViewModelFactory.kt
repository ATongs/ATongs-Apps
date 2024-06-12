package com.project.capstone.atongs_md.ui.detection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.capstone.atongs_md.data.di.Injection
import com.project.capstone.atongs_md.data.repository.DetectionRepository

class DetectionViewModelFactory private constructor(
    private val detectionRepository: DetectionRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetectionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetectionViewModel(detectionRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        @Volatile
        private var instance: DetectionViewModelFactory? = null
        fun getInstance(
        ): DetectionViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: DetectionViewModelFactory(
                    Injection.provideDetectionRepository()
                )
            }.also { instance = it }
    }
}
