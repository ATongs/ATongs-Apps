package com.project.capstone.atongs_md.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.capstone.atongs_md.data.Result
import com.project.capstone.atongs_md.data.repository.HistoryRepository
import com.project.capstone.atongs_md.data.response.DataItem

class HistoryViewModel(historyRepository: HistoryRepository): ViewModel(){

    val listHistory: LiveData<Result<List<DataItem>>> = historyRepository.getListHistory()
}
