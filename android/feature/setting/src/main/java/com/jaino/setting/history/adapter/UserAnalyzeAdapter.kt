package com.jaino.setting.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jaino.model.user.Analysis
import com.jaino.setting.databinding.ItemUserAnalyzeBinding


class UserAnalyzeAdapter(
    private val onItemClick : (Long) -> Unit
) : ListAdapter<Analysis,
        UserAnalyzeAdapter.UserAnalyzeViewHolder>(UserAnalyzeCallback) {

    companion object {
        val UserAnalyzeCallback = object : DiffUtil.ItemCallback<Analysis>(){
            override fun areItemsTheSame(
                oldItem: Analysis, newItem: Analysis): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Analysis, newItem: Analysis
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAnalyzeViewHolder {
        return UserAnalyzeViewHolder(ItemUserAnalyzeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: UserAnalyzeViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class UserAnalyzeViewHolder(private val binding : ItemUserAnalyzeBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Analysis) {
            binding.item = item
            binding.root.setOnClickListener {
                onItemClick(item.id)
            }
        }
    }
}