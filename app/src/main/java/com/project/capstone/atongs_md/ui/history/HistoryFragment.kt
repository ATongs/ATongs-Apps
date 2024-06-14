package com.project.capstone.atongs_md.ui.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.capstone.atongs_md.data.Result
import com.project.capstone.atongs_md.data.adapter.HistoryAdapter
import com.project.capstone.atongs_md.data.response.DataItem
import com.project.capstone.atongs_md.databinding.FragmentHistoryBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root = binding.root

        setupViewModel()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeListHistory()

    }
    private fun setupViewModel() {
        val factory: HistoryViewModelFactory = HistoryViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[HistoryViewModel::class.java]
    }

    private fun observeListHistory() {
        viewModel.listHistory.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is Result.Success -> {
                    binding.progressBar.isVisible = false
                    historyList(it.data)
                }
                is Result.Error -> {
                    binding.progressBar.isVisible = false
                    Log.e("HistoryFragment", "Error fetching history", it.exception)
                    Toast.makeText(requireContext(), "An error occurred: ${it.exception.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun historyList(historyItem: List<DataItem>) {
        // Sort the history items by createdAt in descending order
        val sortedHistoryItem = historyItem.sortedByDescending { it.createdAt?.toDate() }

        val historyAdapter = HistoryAdapter()
        historyAdapter.submitList(sortedHistoryItem)
        binding.rvHistory.adapter = historyAdapter
        binding.rvHistory.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun String.toDate(): Date? {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        return try {
            format.parse(this)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
