package com.jaino.setting.history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getDrawable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jaino.model.analysis.AnalysisHistory
import com.jaino.setting.databinding.ItemUserAnalyzeBinding

class AnalysisHistoryAdapter(
    private val context : Context,
    private val onItemClick : (Long) -> Unit
) : ListAdapter<AnalysisHistory,
        AnalysisHistoryAdapter.UserAnalyzeViewHolder>(UserAnalyzeCallback) {

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
        fun bind(item : AnalysisHistory) {
            binding.item = item
            val sentiment = item.sentiment.slice(0 .. 1)
            if(sentiment == SAD){
                binding.imageView6.setImageDrawable(getDrawable(context, SAD_ICON))
            }
            else if(sentiment == HAPPY){
                binding.imageView6.setImageDrawable(getDrawable(context, HAPPY_ICON))
            }
            binding.root.setOnClickListener {
                onItemClick(item.id)
            }
        }
    }

    companion object {
        val UserAnalyzeCallback = object : DiffUtil.ItemCallback<AnalysisHistory>(){
            override fun areItemsTheSame(
                oldItem: AnalysisHistory, newItem: AnalysisHistory
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: AnalysisHistory, newItem: AnalysisHistory
            ): Boolean {
                return oldItem == newItem
            }
        }
        val SAD_ICON = com.jaino.designsystem.R.drawable.sad
        val HAPPY_ICON = com.jaino.designsystem.R.drawable.smile
        const val SAD = "슬픔"
        const val HAPPY = "기쁨"
    }
}