package com.jaino.analyze.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jaino.analyze.databinding.ItemRankBinding
import com.jaino.model.dictionary.Drink

class HomeRankAdapter(
    private val itemClick : (Long) -> Unit
) : ListAdapter<Drink, HomeRankAdapter.RankViewHolder>(callback) {

    companion object{
        val callback = object : DiffUtil.ItemCallback<Drink>(){
            override fun areItemsTheSame(oldItem: Drink, newItem: Drink): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Drink, newItem: Drink): Boolean {
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
        fun bind(item : Drink){
            binding.item = item
            binding.root.setOnClickListener {
                itemClick(item.id)
            }
        }
    }
}
