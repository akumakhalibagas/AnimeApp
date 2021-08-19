package com.makhalibagas.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.makhalibagas.animeaja.di.BookmarkModuleDependencies
import com.makhalibagas.animeaja.ui.DetailActivity
import com.makhalibagas.core.domain.model.Anime
import com.makhalibagas.core.ui.AnimeAdapter
import com.makhalibagas.core.ui.MangaAdapter
import com.makhalibagas.favorite.databinding.FragmentBookmarkBinding
import com.makhalibagas.favorite.di.DaggerBookmarkModule
import com.makhalibagas.favorite.viewmodel.BookmarkViewModel
import com.makhalibagas.favorite.viewmodel.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject
import kotlin.math.abs

class BookmarkFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val bookmarkViewModel: BookmarkViewModel by viewModels { viewModelFactory }
    private lateinit var animeAdapter: AnimeAdapter
    private lateinit var mangaAdapter: MangaAdapter
    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding as FragmentBookmarkBinding
    private val handler: Handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerBookmarkModule.builder()
            .context(context)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity().applicationContext,
                    BookmarkModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            setupAnime()
            setupManga()
        }
        observeAnime()
        observeManga()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvManga.adapter = null
        binding.vpAnime.adapter = null
        _binding = null
    }

    private fun FragmentBookmarkBinding.setupAnime() {
        animeAdapter = AnimeAdapter(vpAnime) { onClickItem(it) }
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

        vpAnime.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(viewpagerrunnable)
                handler.postDelayed(viewpagerrunnable, 2000)
            }
        })
    }

    private fun FragmentBookmarkBinding.setupManga() {
        mangaAdapter = MangaAdapter { onClickItem(it) }
        rvManga.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        rvManga.adapter = mangaAdapter
    }

    private fun observeAnime() {
        bookmarkViewModel.bookmarkAnimes.observe(viewLifecycleOwner, {
            animeAdapter.setAnimes(it)
        })
    }

    private fun observeManga() {
        bookmarkViewModel.bookmarkMangas.observe(viewLifecycleOwner, {
            mangaAdapter.setMangas(it)
        })
    }

    private fun onClickItem(anime: Anime) {
        startActivity(Intent(requireContext(), DetailActivity::class.java).also {
            it.putExtra("data", anime)
        })
    }
}