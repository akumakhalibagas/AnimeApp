package com.makhalibagas.animeaja.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.makhalibagas.animeaja.databinding.FragmentAnimeBinding
import com.makhalibagas.animeaja.viewmodel.AnimeViewModel
import com.makhalibagas.core.data.Resource
import com.makhalibagas.core.domain.model.Anime
import com.makhalibagas.core.ui.AnimeAdapter
import com.makhalibagas.core.ui.MangaAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlin.math.abs
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class AnimeFragment : Fragment() {

    private var _binding: FragmentAnimeBinding? = null
    private val binding get() = _binding as FragmentAnimeBinding
    private val viewModel: AnimeViewModel by viewModels()
    private lateinit var animeAdapter: AnimeAdapter
    private lateinit var mangaAdapter: MangaAdapter
    private val handler: Handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            setupAnime()
            setupManga()
            observeAnime()
            observeManga()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvManga.adapter = null
        binding.vpAnime.adapter = null
        _binding = null
    }

    private fun FragmentAnimeBinding.setupAnime() {
        animeAdapter = AnimeAdapter(vpAnime) { onClickItem(it)}
        vpAnime.clipToPadding = false
        vpAnime.clipChildren = false
        vpAnime.offscreenPageLimit = 3
        vpAnime.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(15))
        compositePageTransformer.addTransformer { page, position ->
            val f = 1 - abs(position)
            page.scaleY = 0.95f + f * 0.03f
        }
        vpAnime.setPageTransformer(compositePageTransformer)
        vpAnime.adapter = animeAdapter
        val viewpagerrunnable =
            Runnable { vpAnime.currentItem = vpAnime.currentItem + 1 }

        vpAnime.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(viewpagerrunnable)
                handler.postDelayed(viewpagerrunnable, 2000)
            }
        })
    }

    private fun FragmentAnimeBinding.setupManga() {
        mangaAdapter = MangaAdapter { onClickItem(it) }
        rvManga.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        rvManga.adapter = mangaAdapter
    }

    private fun FragmentAnimeBinding.observeAnime() {
        viewModel.animes.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> loading.visibility = View.VISIBLE
                is Resource.Success -> {
                    animeAdapter.setAnimes(it.data)
                    loading.visibility = View.INVISIBLE
                }
                is Resource.Error -> Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun FragmentAnimeBinding.observeManga() {
        viewModel.manga.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> loading.visibility = View.VISIBLE
                is Resource.Success -> {
                    mangaAdapter.setMangas(it.data)
                    loading.visibility = View.INVISIBLE
                }
                is Resource.Error -> Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun onClickItem(anime: Anime) {
        startActivity(Intent(requireContext(), DetailActivity::class.java).also {
            it.putExtra("data", anime)
        })
    }
}