package com.jaino.analysis.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jaino.analysis.databinding.ItemRankBinding
import com.jaino.model.rank.Rank

class HomeRankAdapter(
    private val itemClick : (Long) -> Unit
) : ListAdapter<Rank, HomeRankAdapter.RankViewHolder>(callback) {

    companion object{
        val callback = object : DiffUtil.ItemCallback<Rank>(){
            override fun areItemsTheSame(oldItem: Rank, newItem: Rank): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Rank, newItem: Rank): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankViewHolder {
        return RankViewHolder(ItemRankBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RankViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class RankViewHolder(private val binding: ItemRankBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Rank){
            binding.item = item
            binding.root.setOnClickListener {
                itemClick(item.id)
            }
        }
    }
}
