package com.jaino.dictionary.drink_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jaino.dictionary.databinding.ItemDrinkListBinding
import com.jaino.model.dictionary.DrinkData

class DrinkDataAdapter :ListAdapter<DrinkData, DrinkDataAdapter.DrinkDataViewHolder>(callback) {

    companion object{
        val callback = object : DiffUtil.ItemCallback<DrinkData>(){
            override fun areItemsTheSame(oldItem: DrinkData, newItem: DrinkData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DrinkData, newItem: DrinkData): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkDataViewHolder {
        return DrinkDataViewHolder(ItemDrinkListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DrinkDataViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class DrinkDataViewHolder(private val binding: ItemDrinkListBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(item : DrinkData){
                binding.item = item
            }
    }
}
