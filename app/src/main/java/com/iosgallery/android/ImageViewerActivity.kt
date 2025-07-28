package com.iosgallery.android

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.iosgallery.android.adapter.FullscreenPhotoAdapter
import com.iosgallery.android.databinding.ActivityImageViewerBinding
import com.iosgallery.android.model.Photo

class ImageViewerActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityImageViewerBinding
    private lateinit var adapter: FullscreenPhotoAdapter
    private var photos: List<Photo> = emptyList()
    private var currentPosition = 0
    private var isToolbarVisible = true
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Get data from intent
        currentPosition = intent.getIntExtra("position", 0)
        photos = intent.getParcelableArrayListExtra<Photo>("photos") ?: emptyList()
        
        setupUI()
        setupViewPager()
    }
    
    private fun setupUI() {
        // Hide system UI for immersive experience
        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        )
        
        // Setup click listeners
        binding.ivBack.setOnClickListener {
            finish()
        }
        
        binding.ivShare.setOnClickListener {
            shareCurrentPhoto()
        }
        
        binding.ivDelete.setOnClickListener {
            deleteCurrentPhoto()
        }
        
        // Toggle toolbar on tap
        binding.viewPager.setOnClickListener {
            toggleToolbar()
        }
    }
    
    private fun setupViewPager() {
        adapter = FullscreenPhotoAdapter(photos) { 
            toggleToolbar()
        }
        
        binding.viewPager.apply {
            adapter = this@ImageViewerActivity.adapter
            setCurrentItem(currentPosition, false)
            
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    currentPosition = position
                }
            })
        }
    }
    
    private fun toggleToolbar() {
        if (isToolbarVisible) {
            hideToolbar()
        } else {
            showToolbar()
        }
        isToolbarVisible = !isToolbarVisible
    }
    
    private fun hideToolbar() {
        binding.topToolbar.animate()
            .translationY(-binding.topToolbar.height.toFloat())
            .setDuration(300)
            .start()
    }
    
    private fun showToolbar() {
        binding.topToolbar.animate()
            .translationY(0f)
            .setDuration(300)
            .start()
    }
    
    private fun shareCurrentPhoto() {
        if (currentPosition < photos.size) {
            val photo = photos[currentPosition]
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "image/*"
                putExtra(Intent.EXTRA_STREAM, android.net.Uri.parse("file://${photo.path}"))
            }
            startActivity(Intent.createChooser(shareIntent, "Share photo"))
        }
    }
    
    private fun deleteCurrentPhoto() {
        if (currentPosition < photos.size) {
            // In a real app, implement photo deletion
            // For now, just finish the activity
            finish()
        }
    }
}