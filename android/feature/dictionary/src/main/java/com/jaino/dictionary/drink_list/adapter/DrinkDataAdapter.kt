package com.jaino.dictionary.drink_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jaino.dictionary.databinding.ItemDrinkListBinding
import com.jaino.model.dictionary.Drink

class DrinkDataAdapter(
    private val itemClick : (Long) -> Unit
) : ListAdapter<Drink, DrinkDataAdapter.DrinkDataViewHolder>(callback) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkDataViewHolder {
        return DrinkDataViewHolder(ItemDrinkListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DrinkDataViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class DrinkDataViewHolder(private val binding: ItemDrinkListBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Drink){
            binding.item = item
            binding.drinkDataItemCardView.setOnClickListener {
                itemClick(item.id)
            }
        }
    }
}
