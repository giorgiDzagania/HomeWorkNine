package com.exercise.tbchomeworknine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.exercise.tbchomeworknine.databinding.SecondRecyclerviewItemsBinding


class OutputAdapter: ListAdapter<Output, OutputAdapter.OutPutViewHolder>(OutputDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutPutViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SecondRecyclerviewItemsBinding.inflate(inflater,parent,false)
        return OutPutViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OutPutViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class OutputDiffCallback: DiffUtil.ItemCallback<Output>() {
        override fun areItemsTheSame(oldItem: Output, newItem: Output): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Output, newItem: Output): Boolean {
            return oldItem == newItem
        }
    }

    class OutPutViewHolder(private val binding: SecondRecyclerviewItemsBinding)
        :RecyclerView.ViewHolder(binding.root){
        fun bind(output: Output){
            binding.ivCombined.setImageResource(output.image)
            binding.tvDescription.text = output.description
            binding.tvPrice.text = output.price
        }
    }
}