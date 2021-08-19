package com.makhalibagas.animeaja.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.makhalibagas.animeaja.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    private val binding: ActivitySettingBinding by lazy {
        ActivitySettingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            changelanguage.setOnClickListener { change() }
            share.setOnClickListener { share() }
            rate.setOnClickListener { rate() }
            author.setOnClickListener { author() }
            back.setOnClickListener { onBackPressed() }
        }
    }

    private fun change() {
        val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
        startActivity(intent)
    }

    private fun share() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "Ayo download" + "\n" + "https://play.google.com/store/apps/dev?id=com.makhalibagas.animeaja"
        )
        intent.type = "text/plain"
        startActivity(intent)
    }

    private fun rate() {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=com.makhalibagas.animeaja")
            )
        )
    }

    private fun author() {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://makhalibagas.me")))
    }
}