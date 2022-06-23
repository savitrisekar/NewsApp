package com.savitrisekar.newsapp.ui.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.savitrisekar.newsapp.R
import com.savitrisekar.newsapp.databinding.ActivityMainBinding
import com.savitrisekar.newsapp.ui.features.favorite.NewsFavoriteFragment
import com.savitrisekar.newsapp.ui.features.home.main.HomeFragment
import com.savitrisekar.newsapp.ui.features.profile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity:AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btmNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val homeFragment = HomeFragment()
        setupFragment(homeFragment)
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    val fragment = HomeFragment()
                    setupFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_favorite -> {
                    val fragment = NewsFavoriteFragment()
                    setupFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_profile -> {
                    val fragment = ProfileFragment()
                    setupFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun setupFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}