package com.savitrisekar.newsapp.ui.features.home.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.savitrisekar.newsapp.data.model.Article
import com.savitrisekar.newsapp.databinding.ItemNewsBinding

class HomeAdapter(private val itemClick: (Article) -> Unit) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var items: MutableList<Article> = mutableListOf()

    fun setItems(items: List<Article>) {
        clearItems()
        addItems(items)
        notifyDataSetChanged()
    }

    fun addItems(items: List<Article>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class HomeViewHolder(
        private val binding: ItemNewsBinding,
        val itemClick: (Article) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: Article) {
            with(item) {
                itemView.setOnClickListener { itemClick(this) }
                binding.ivItemNews.load(urlToImage)
                binding.tvTitleNew.text = title
                binding.tvDescribe.text = description
            }
        }
    }
}