package com.savitrisekar.newsapp.ui.features.home.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.savitrisekar.newsapp.data.model.Category
import com.savitrisekar.newsapp.databinding.ItemCategoryBinding

class CategoryAdapter(private val itemClick: (Category) -> Unit) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var items: MutableList<Category> = mutableListOf()

    fun setItems(items: List<Category>) {
        clearItems()
        addItems(items)
        notifyDataSetChanged()
    }

    fun addItems(items: List<Category>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class CategoryViewHolder(
        private val binding: ItemCategoryBinding,
        val itemClick: (Category) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(items: Category) {
            with(items) {
//                if (itemView.isClickable) {
//                    itemClick(this)
//                    binding.tvCategory.setBackgroundResource(R.color.color_dark_grey)
//                } else {
//                    binding.tvCategory.setBackgroundResource(R.color.color_dark_grey)
//                }
                itemView.setOnClickListener { itemClick(this) }
                binding.tvCategory.text = name
            }
        }
    }
}