package com.makhalibagas.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.makhalibagas.core.R
import com.makhalibagas.core.databinding.ItemAnimeBinding
import com.makhalibagas.core.domain.model.Anime

class AnimeAdapter(private val vp: ViewPager2, private val showDetail: (Anime) -> Unit) :
    RecyclerView.Adapter<AnimeAdapter.ViewHolder>() {

    private val animes = ArrayList<Anime>()
    fun setAnimes(listAnimes: List<Anime>?) {
        if (listAnimes == null) return
        this.animes.clear()
        this.animes.addAll(listAnimes)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemAnimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(anime: Anime) {
            with(binding) {

                Glide.with(itemView.context).load(anime.image)
                    .transition(DrawableTransitionOptions.withCrossFade()).placeholder(R.color.gray)
                    .into(animeImage)
                animeTitle.text = anime.title
                animeAge.text = anime.age
                itemView.setOnClickListener {
                    showDetail(anime)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeAdapter.ViewHolder {
        return ViewHolder(
            ItemAnimeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AnimeAdapter.ViewHolder, position: Int) {
        val anime = animes[position]
        holder.bind(anime)
        if (position == animes.size - 2) {
            vp.post(runnable)
        }
    }

    override fun getItemCount(): Int = animes.size

    private val runnable = Runnable {
        animes.addAll(animes)
        notifyDataSetChanged()
    }
}