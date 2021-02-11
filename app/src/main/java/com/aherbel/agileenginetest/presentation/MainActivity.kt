package com.aherbel.agileenginetest.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.aherbel.agileenginetest.R
import com.aherbel.agileenginetest.presentation.screens.imagelist.ImagesListFragment

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        setTitle(R.string.gallery_app)
        
        supportFragmentManager.commit {
            add(R.id.container, ImagesListFragment(), ImagesListFragment.TAG)
        }
    }
    
    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        }
    }
    
}