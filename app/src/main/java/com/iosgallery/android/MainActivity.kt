package com.iosgallery.android

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iosgallery.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SimplePhotoAdapter
    
    companion object {
        private const val PERMISSION_REQUEST_CODE = 123
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupUI()
        checkPermissions()
    }
    
    private fun setupUI() {
        // Simple grid with dummy data
        val dummyPhotos = (1..20).map { "Photo $it" }
        adapter = SimplePhotoAdapter(dummyPhotos)
        
        binding.rvPhotos.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = this@MainActivity.adapter
        }
        
        // Hide other sections for simplicity
        binding.tvRecentDays.visibility = android.view.View.GONE
        binding.rvRecentDays.visibility = android.view.View.GONE
        binding.tvPeopleAndPets.visibility = android.view.View.GONE
        binding.rvPeopleAndPets.visibility = android.view.View.GONE
    }
    
    private fun checkPermissions() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            binding.layoutPermission.visibility = android.view.View.GONE
            Toast.makeText(this, "Permission granted - Ready to load photos", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(permission), PERMISSION_REQUEST_CODE)
        }
    }
    
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                binding.layoutPermission.visibility = android.view.View.GONE
                Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show()
            } else {
                binding.layoutPermission.visibility = android.view.View.VISIBLE
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}