package com.makhalibagas.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.makhalibagas.core.R
import com.makhalibagas.core.databinding.ItemMangaBinding
import com.makhalibagas.core.domain.model.Anime

class MangaAdapter(private val showDetail: (Anime) -> Unit) :
    RecyclerView.Adapter<MangaAdapter.ViewHolder>() {

    private val mangas = ArrayList<Anime>()
    fun setMangas(listMangas: List<Anime>?) {
        if (listMangas == null) return
        this.mangas.clear()
        this.mangas.addAll(listMangas)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemMangaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(manga: Anime) {
            with(binding) {

                Glide.with(itemView.context).load(manga.image).transition(DrawableTransitionOptions.withCrossFade()).placeholder(
                    R.color.gray).into(animeImage)
                animeTitle.text = manga.title
                animeAge.text = manga.age
                animeStatus.text = manga.status
                itemView.setOnClickListener {
                    showDetail(manga)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaAdapter.ViewHolder {
        return ViewHolder(
            ItemMangaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MangaAdapter.ViewHolder, position: Int) {
        val anime = mangas[position]
        holder.bind(anime)
    }

    override fun getItemCount(): Int = mangas.size
}