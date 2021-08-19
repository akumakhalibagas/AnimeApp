package com.makhalibagas.animeaja.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.makhalibagas.animeaja.R
import com.makhalibagas.animeaja.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            curvedBottomNavigation.menu.getItem(1).isEnabled = false
            curvedBottomNavigation.setupWithNavController(findNavController(R.id.nav_host_fragment))
            fabSettings.setOnClickListener {
                startActivity(Intent(applicationContext, SettingActivity::class.java))
            }
        }
    }
}