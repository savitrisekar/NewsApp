package com.savitrisekar.newsapp.ui.features.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.savitrisekar.newsapp.data.model.Article
import com.savitrisekar.newsapp.databinding.ItemNewsBinding

class NewsFavoriteAdapter(private val itemClick: (Article) -> Unit) :
    RecyclerView.Adapter<NewsFavoriteAdapter.NewsFavoriteViewHolder>() {

    private var items: MutableList<Article> = mutableListOf()

    fun setItems(items: List<Article>) {
        clearItems()
        addItems(items)
        notifyDataSetChanged()
    }

    private fun addItems(items: List<Article>) {
        this.items.addAll(items)
    }

    private fun clearItems() {
        this.items.clear()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsFavoriteViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsFavoriteViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(
        holder: NewsFavoriteViewHolder,
        position: Int
    ) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class NewsFavoriteViewHolder(
        private val binding: ItemNewsBinding,
        val itemClick: (Article) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
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