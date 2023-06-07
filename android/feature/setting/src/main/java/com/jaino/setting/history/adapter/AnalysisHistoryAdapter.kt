package com.jaino.setting.history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getDrawable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jaino.common.constant.HAPPY
import com.jaino.common.constant.MEDIAN
import com.jaino.common.constant.SAD
import com.jaino.common.extensions.toSentimentKor
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
            val sentiment = item.sentiment.filter { it.isLetter() }.toSentimentKor()
            binding.item = item.copy(sentiment = sentiment)
            when(item.sentiment){
                SAD -> {
                    binding.imageView6.setImageDrawable(getDrawable(context, SAD_ICON))
                }
                HAPPY -> {
                    binding.imageView6.setImageDrawable(getDrawable(context, HAPPY_ICON))
                }
                MEDIAN -> {
                    binding.imageView6.setImageDrawable(getDrawable(context, MEDIAN_ICON))
                }
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
        val MEDIAN_ICON = com.jaino.designsystem.R.drawable.neutral
    }
}