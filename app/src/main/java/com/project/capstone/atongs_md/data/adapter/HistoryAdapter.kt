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
import com.project.capstone.atongs_md.data.response.DataItem
import com.project.capstone.atongs_md.databinding.ItemHistoryBinding
import com.project.capstone.atongs_md.ui.history.detailhistory.DetailHistoryActivity

class HistoryAdapter : ListAdapter<DataItem, HistoryAdapter.ListViewHolder>(diffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryAdapter.ListViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ListViewHolder, position: Int) {
        val history = getItem(position)
        holder.bind(history)
    }

    inner class ListViewHolder(private var binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(history: DataItem) {
            Glide.with(binding.root.context)
                .load(history.imageUrl ?: "")
                .apply(RequestOptions().override(80, 80).placeholder(R.drawable.ic_place_holder))
                .transform(CircleCrop())
                .into(binding.imgItemHistory)

            binding.tvDetectionCategory.text = history.result?.label ?: "No Title"
            binding.tvDetection.text = history.result?.message ?: "No Description"

            binding.root.setOnClickListener {
                val context = binding.root.context
                val intent = Intent(context, DetailHistoryActivity::class.java).apply {
                    putExtra(DetailHistoryActivity.EXTRA_TITLE, history.result?.label ?: "No Title")
                    putExtra(DetailHistoryActivity.EXTRA_IMAGE_URL, history.imageUrl ?: "")
                    putExtra(DetailHistoryActivity.EXTRA_DATA_ITEM, history)  // Pass the DataItem
                }
                context.startActivity(intent)
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
