package com.jaino.dictionary.drink_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jaino.dictionary.databinding.ItemDrinkListBinding
import com.jaino.model.dictionary.DrinkInfo

class DrinkDataAdapter(
    private val itemClick : (Long) -> Unit
) : ListAdapter<DrinkInfo, DrinkDataAdapter.DrinkDataViewHolder>(callback) {

    companion object{
        val callback = object : DiffUtil.ItemCallback<DrinkInfo>(){
            override fun areItemsTheSame(oldItem: DrinkInfo, newItem: DrinkInfo): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DrinkInfo, newItem: DrinkInfo): Boolean {
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
        fun bind(item : DrinkInfo){
            binding.item = item

            //TODO 실제 id 값으로 연결 현재 model에 id 값 빠져 있음.
            binding.drinkDataItemCardView.setOnClickListener {
                itemClick(30)
            }
        }
    }
}
