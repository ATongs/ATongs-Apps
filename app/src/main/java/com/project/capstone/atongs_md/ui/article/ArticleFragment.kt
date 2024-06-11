package com.project.capstone.atongs_md.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.capstone.atongs_md.data.adapter.ArticleAdapter
import com.project.capstone.atongs_md.databinding.FragmentArticleBinding
import com.project.capstone.atongs_md.data.Result

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ArticleViewModel
    private val adapter by lazy { ArticleAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val root = binding.root

        setupViewModel()
        setupRecyclerView()
        observerListUser()

        return root
    }

    private fun setupViewModel() {
        val factory: ArticleViewModelFactory = ArticleViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[ArticleViewModel::class.java]
    }

    private fun setupRecyclerView() {
        binding.rvArticle.setHasFixedSize(true)
        binding.rvArticle.layoutManager = LinearLayoutManager(requireContext())
        binding.rvArticle.adapter = adapter
    }

    private fun observerListUser() {
        viewModel.listArticle.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is Result.Success -> {
                    binding.progressBar.isVisible = false
                    adapter.submitList(it.data)
                }
                is Result.Error -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(requireContext(),"An error occurred, reopen it", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
