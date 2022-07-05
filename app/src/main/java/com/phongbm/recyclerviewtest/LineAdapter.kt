package com.phongbm.recyclerviewtest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.phongbm.recyclerviewtest.databinding.ViewHolderLineBinding

/**
 * Create by PhongBM on 07/05/2022
 */

class LineAdapter : ListAdapter<String, LineAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem
            override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderLineBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(
        private val binding: ViewHolderLineBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val line = getItem(position) ?: return
            binding.txtData.text = line
        }
    }

}