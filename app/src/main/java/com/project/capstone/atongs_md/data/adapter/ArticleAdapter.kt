package com.project.capstone.atongs_md.data.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.project.capstone.atongs_md.R
import com.project.capstone.atongs_md.data.response.ArticlesItem
import com.project.capstone.atongs_md.databinding.ItemArticleBinding
import com.project.capstone.atongs_md.ui.article.detailarticle.DetailArticleActivity

class ArticleAdapter : ListAdapter<ArticlesItem, ArticleAdapter.ListViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }

    inner class ListViewHolder(private var binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: ArticlesItem) {
            Glide.with(binding.root.context)
                .load(article.urlToImage ?: "")
                .apply(RequestOptions().override(80, 80).placeholder(R.drawable.ic_place_holder))
                .transform(CircleCrop())
                .into(binding.ivImage)

            binding.tvTitle.text = article.title ?: "No Title"
            binding.tvDescription.text = article.description ?: "No Description"

            binding.root.setOnClickListener {
                val context = binding.root.context
                val intent = Intent(context, DetailArticleActivity::class.java).apply {
                    putExtra(DetailArticleActivity.EXTRA_TITLE, article.title ?: "No Title")
                    putExtra(DetailArticleActivity.EXTRA_DESCRIPTION, article.description ?: "No Description")
                    putExtra(DetailArticleActivity.EXTRA_IMAGE_URL, article.urlToImage ?: "")
                }
                context.startActivity(intent)
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ArticlesItem>() {
            override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
