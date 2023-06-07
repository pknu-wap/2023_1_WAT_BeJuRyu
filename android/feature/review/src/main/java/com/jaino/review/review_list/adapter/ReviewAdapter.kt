package com.jaino.review.review_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import model.review.ReviewModel
import com.jaino.review.databinding.ItemReviewBinding

class ReviewAdapter : ListAdapter<ReviewModel, ReviewAdapter.ReviewViewHolder>(callback) {

    companion object{
        val callback = object : DiffUtil.ItemCallback<ReviewModel>(){
            override fun areContentsTheSame(oldItem: ReviewModel, newItem: ReviewModel): Boolean {
                return oldItem.reviewId == newItem.reviewId
            }

            override fun areItemsTheSame(oldItem: ReviewModel, newItem: ReviewModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(ItemReviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ReviewViewHolder(private val binding : ItemReviewBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(item : ReviewModel){
            binding.item = item
        }
    }
}