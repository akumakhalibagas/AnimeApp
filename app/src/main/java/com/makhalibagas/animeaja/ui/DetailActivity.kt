package com.makhalibagas.animeaja.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.makhalibagas.animeaja.databinding.ActivityDetailBinding
import com.makhalibagas.animeaja.databinding.ReadMoreBinding
import com.makhalibagas.animeaja.viewmodel.DetailViewModel
import com.makhalibagas.core.domain.model.Anime
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val anime = intent.getParcelableExtra<Anime>("data")
        with(binding) {
            showDetail(anime!!)
            onClick()
        }
    }

    private fun ActivityDetailBinding.showDetail(anime: Anime) {
        if (anime.key.isNullOrEmpty()) {
            Glide.with(applicationContext).load(anime.image).into(detailTrailer)
        } else {
            Glide.with(applicationContext).load("https://i.ytimg.com/vi/${anime.key}/hqdefault.jpg")
                .into(detailTrailer)
            playTrailer.visibility = View.VISIBLE
            playTrailer.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=${anime.key}")))
            }
        }

        Glide.with(applicationContext).load(anime.image).into(detailImage)
        detailTitle.text = anime.title
        detailStatus.text = anime.status
        detailSynopsis.text = anime.decs + "..."
        detailReadMore.setOnClickListener {
            readMore(anime.decs!!)
        }

        var isBookmark = anime.isBookmark
        isBookmark(isBookmark)

        ibBookmark.setOnClickListener {
            isBookmark = !isBookmark
            viewModel.setBookmark(anime, isBookmark)
            isBookmark(isBookmark)
        }

        ibBookmarked.setOnClickListener {
            isBookmark = !isBookmark
            viewModel.setBookmark(anime, isBookmark)
            isBookmark(isBookmark)
        }
    }

    private fun ActivityDetailBinding.isBookmark(status: Boolean) {
        if (status) {
            ibBookmarked.visibility = View.VISIBLE
            ibBookmark.visibility = View.INVISIBLE
        } else {
            ibBookmarked.visibility = View.INVISIBLE
            ibBookmark.visibility = View.VISIBLE
        }
    }

    private fun ActivityDetailBinding.onClick() {
        back.setOnClickListener { onBackPressed() }
    }

    private fun readMore(synopsis : String){
        val dialog = BottomSheetDialog(this)
        val bindingDialog = ReadMoreBinding.inflate(layoutInflater)
        dialog.setContentView(bindingDialog.root)
        bindingDialog.synopsis.text = synopsis
        dialog.show()
    }
}