package com.exercise.tbchomeworknine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.exercise.tbchomeworknine.databinding.FirtItemRecyclerBinding
import com.exercise.tbchomeworknine.databinding.ItemsRecyclerBinding

class Adapter(private val categoryClickListener: CategoryClickListener)
    : ListAdapter<Choice, RecyclerView.ViewHolder>(ChoiceDiffCallback()) {

    interface CategoryClickListener {
        fun onCategoryClicked(type: TypeOfItem)
    }

    companion object {
        const val VIEW_TYPE_NORMAL = 1
        const val VIEW_TYPE_DIFFERENT = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                val binding = ItemsRecyclerBinding.inflate(inflater, parent, false)
                ViewHolder(binding)
            }
            VIEW_TYPE_DIFFERENT -> {
                val binding = FirtItemRecyclerBinding.inflate(inflater, parent, false)
                DifferentViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val currentItem = getItem(position)
                holder.bind(currentItem)
            }
            is DifferentViewHolder -> {
                val currentItem = getItem(position)
                holder.bind(currentItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).type) {
            ItemType.NORMAL -> VIEW_TYPE_NORMAL
            ItemType.DIFFERENT -> VIEW_TYPE_DIFFERENT
        }
    }

    class ChoiceDiffCallback : DiffUtil.ItemCallback<Choice>() {
        override fun areItemsTheSame(oldItem: Choice, newItem: Choice): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Choice, newItem: Choice): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: ItemsRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(choice: Choice) {
            binding.ivImage.setImageResource(choice.image)
            binding.tvTitle.text = choice.title
            binding.root.setOnClickListener {
                val categoryType = when (adapterPosition) {
                    0 -> TypeOfItem.ALL
                    1 -> TypeOfItem.Party
                    2 -> TypeOfItem.Camping
                    3 -> TypeOfItem.Catergory1
                    4 -> TypeOfItem.Catergory2
                    5 -> TypeOfItem.Catergory3
                    else -> TypeOfItem.ALL
                }
                categoryClickListener.onCategoryClicked(categoryType)
            }
        }
    }

    inner class DifferentViewHolder(private val binding: FirtItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(choice: Choice) {
            binding.firstItem.text = choice.title

            binding.root.setOnClickListener {
                categoryClickListener.onCategoryClicked(TypeOfItem.ALL)
            }
        }
    }
}